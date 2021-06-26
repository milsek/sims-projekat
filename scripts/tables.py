import random
import requests


class EDITION:
    instances = []

    def __init__(self, info, tag_names):
        # fill fields with obtained or randomly generated values
        self.id = len(EDITION.instances)
        self.available_copies = random.randint(5, 60)
        self.description = info.get('description')
        self.dimensions = random.randint(18, 22)
        self.image_large = info.get('imageLinks').get('thumbnail').replace('edge=curl&', '').replace('zoom=1&', '')
        self.image_small = info.get('imageLinks').get('thumbnail').replace('edge=curl&', '')
        self.language = info.get('language')
        self.page_count = info.get('pageCount')
        self.rating = info.get('rating')
        self.reads = random.randint(50, 600)
        self.take_out = random.choice(['true']*5 + ['false'])
        self.year = info.get('publishedDate')

        # collect data for supplying other tables and fields
        self.author_names = info.get('authors')
        self.genre_names = info.get('categories')
        self.publisher_name = info.get('publisher')
        self.tag_names = tag_names
        self.title = info.get('title')

        # check if any cruical data is missing
        if None in [self.description, self.genre_names, self.author_names, self.publisher_name]:
            return

        # detect placeholder cover picture
        if len(requests.get(self.image_large).content) in [9103, 316]: 
            return

        # fill missing data with randomly generated values
        if not self.rating:
            self.rating = random.randint(10, 50) / 10
        if not self.page_count:
            self.page_count = random.randint(100, 300)
        if self.year:
            self.year = info.get('publishedDate')[:4]
        else:
            self.year = '2021'

        # get other ids
        self.publisher_id = PUBLISHER.with_name(self.publisher_name).id

        # generate related instances if they do not exist
        for name in self.author_names:
            CONTRIBUTION.with_contrib_and_type(CONTRIBUTOR.with_name(name), 'author')
        for name in self.genre_names:
            CONTENT.with_name(name)
        for name in self.tag_names:
            TAG.with_name(name)

        EDITION.instances.append(self)
        print(f"[+] Faund {len(EDITION.instances)} buks")

    def toSql(self):
        sql = [f"INSERT INTO BOOK_TITLE VALUES({self.id}, '{escape(self.title)}');",
               f"INSERT INTO EDITION VALUES({self.id}, {self.available_copies}, '{trim(escape(self.description))}', '{self.dimensions}', '{self.image_large}', "
               f"'{self.image_small}', '{self.language}', {self.page_count}, {self.rating}, {self.reads}, {self.take_out}, PARSEDATETIME('{self.year}', 'yyyy'), "
               f"{self.publisher_id});"]

        for name in self.author_names:
            contributor = CONTRIBUTOR.with_name(name)
            contribution = CONTRIBUTION.with_contrib_and_type(contributor, 'author')
            sql.append(f"INSERT INTO EDITION_CONTRIBUTIONS VALUES({self.id}, {contributor.id});")
        for name in self.genre_names:
            genre = CONTENT.with_name(name)
            sql.append(f"INSERT INTO EDITION_CONTENT VALUES({self.id}, {genre.id});")
        for name in self.tag_names:
            tag = TAG.with_name(name)
            sql.append(f"INSERT INTO EDITION_TAGS VALUES({self.id}, {tag.id});")


        return sql


class CONTRIBUTION:
    instances = []

    def __init__(self, contributor, ctype):
        self.id = len(CONTRIBUTION.instances)
        self.contributor = contributor
        self.ctype = ctype

        CONTRIBUTION.instances.append(self)

    def with_contrib_and_type(contributor, ctype):
        for contribution in CONTRIBUTION.instances:
            if contribution.contributor.id == contributor.id and contribution.ctype == ctype:
                return contribution
        return CONTRIBUTION(contributor, ctype)

    def toSql(self):
        return f"INSERT INTO CONTRIBUTION VALUES({self.id}, {self.contributor.id}, '{self.ctype}'"



class CONTRIBUTOR:
    instances = []
    map = {}

    def __init__(self, name):
        self.id = len(CONTRIBUTOR.instances)
        self.name = name

        CONTRIBUTOR.instances.append(self)
        CONTRIBUTOR.map[name] = self

    @staticmethod
    def with_name(name):
        if CONTRIBUTOR.map.get(name):
            return CONTRIBUTOR.map.get(name)
        else:
            return CONTRIBUTOR(name)

    def toSql(self):
        return f"INSERT INTO CONTRIBUTOR VALUES({self.id}, '{escape(self.name)}');"


class PUBLISHER:
    instances = []
    map = {}

    def __init__(self, name):
        self.id = len(PUBLISHER.instances)
        self.name = name

        PUBLISHER.instances.append(self)
        PUBLISHER.map[name] = self

    @staticmethod
    def with_name(name):
        if PUBLISHER.map.get(name):
            return PUBLISHER.map.get(name)
        else:
            return PUBLISHER(name)

    def toSql(self):
        return f"INSERT INTO PUBLISHER VALUES({self.id}, '{escape(self.name)}');"


class TAG:
    instances = []
    map = {}

    def __init__(self, name):
        self.id = len(TAG.instances)
        self.name = name

        TAG.instances.append(self)
        TAG.map[name] = self

    @staticmethod
    def with_name(name):
        if TAG.map.get(name):
            return TAG.map.get(name)
        else:
            return TAG(name)

    def toSql(self):
        return f"INSERT INTO TAG VALUES({self.id}, '{escape(self.name)}');"


class CONTENT:
    instances = []
    map = {}

    def __init__(self, name):
        self.id = len(CONTENT.instances)
        self.name = name

        CONTENT.instances.append(self)
        CONTENT.map[name] = self

    @staticmethod
    def with_name(name):
        if CONTENT.map.get(name):
            return CONTENT.map.get(name)
        else:
            return CONTENT(name)

    def toSql(self):
        return f"INSERT INTO CONTENT VALUES({self.id}, '{escape(self.name)}');"


def escape(string):
    return string.replace("'", "''")


def trim(string):
    return string[:900] + string[900:string.find(".", 900) + 1][:100]
