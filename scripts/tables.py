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

        # collect data for supplying other tables
        self.author_names = info.get('authors')
        self.genre_names = info.get('categories')
        self.publisher_name = info.get('publisher')
        self.tag_names = tag_names

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

        EDITION.instances.append(self)
        print(f"[+] Faund {len(EDITION.instances)} buks")

    def toSql(self):
        sql = [f"INSERT INTO BOOK_TITLE VALUES({self.id}, '{escape(self.title)}');",
               f"INSERT INTO EDITION VALUES({self.id}, {self.available_copies}, '{trim(escape(self.description))}', '{self.dimensions}', '{self.image_large}', "
               f"'{self.image_small}', '{self.language}', {self.page_count}, {self.rating}, {self.reads}, {self.take_out}, PARSEDATETIME('{self.year}', 'yyyy'), "
               f"{self.publisher_id});"]

        # TODO: Other tables

        return sql


class PERSON:
    instances = []
    author_map = {}
    contributor_map = {}

    def __init__(self, name):
        self.id = len(PERSON.instances)
        self.name = name

        PERSON.instances.append(self)

    @staticmethod
    def author_with_name(name):
        if PERSON.author_map.get(name):
            return PERSON.author_map.get(name)
        elif PERSON.contributor_map.get(name):
            person = PERSON.contributor_map.get(name)
            PERSON.author_map[name] = person
            return person
        else:
            new_person = PERSON(name)
            PERSON.author_map[name] = new_person
            return new_person

    @staticmethod
    def contributor_with_name(name):
        if PERSON.contributor_map.get(name):
            return PERSON.contributor_map.get(name)
        elif PERSON.author_map.get(name):
            person = PERSON.author_map.get(name)
            PERSON.contributor_map[name] = person
            return person
        else:
            new_person = PERSON(name)
            PERSON.contributor_map[name] = new_person
            return new_person

    def toSql(self):
        sql = [f"INSERT INTO PERSON VALUES({self.id}, '{escape(self.name)}');"]

        if PERSON.contributor_map.get(self.name):
            sql.append(f"INSERT INTO CONTRIBUTOR VALUES({self.id}, 0);")
        if PERSON.author_map.get(self.name):
            sql.append(f"INSERT INTO AUTHOR VALUES({self.id});")

        return sql


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
