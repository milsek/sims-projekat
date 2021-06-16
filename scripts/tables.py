import random
import requests


class EDITION:
    instances = []

    def __init__(self, info):
        valid = False

        self.id = len(EDITION.instances)
        self.available_copies = random.randint(5, 60)
        self.title = info.get('title').replace("'", "''")

        author_name = info.get('authors')[0]
        publisher_name = info.get('publisher')

        self.contributor_names = info.get('authors')[1:]
        self.tag_names = ['tagovi', 'idu', 'ovde']
        self.content_names = info.get('categories')

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
        self.id = len(EDITION.instances)

        if len(requests.get(self.image_large).content) in [9103, 316]:
            return
        if self.description:
            self.description = self.description.replace("'", "''")
        else:
            return
        if not self.content_names:
            return
        if not self.rating:
            self.rating = random.randint(10, 50) / 10
        if self.year:
            self.year = info.get('publishedDate')[:4]
        else:
            self.year = '2021'

        self.author_id = PERSON.author_with_name(author_name).id
        self.publisher_id = PUBLISHER.with_name(publisher_name).id

    def toSql(self):
        sql = [f"INSERT INTO BOOK_TYPE VALUES ({self.id}, {self.available_copies}, '{self.title}');",
               f"INSERT INTO EDITION VALUES ('{self.description}', '{self.dimensions}', '{self.image_large}', '{self.image_small}', "
               f"'{self.language}', {self.page_count}, {self.rating}, {self.reads}, {self.take_out}, PARSEDATETIME('{self.year}', 'yyyy'), "
               f"{self.id}, {self.author_id}, {self.publisher_id});"]

        for contrib_name in self.contributor_names:
            contrib = PERSON.contributor_with_name(contrib_name)
            sql.append(f"INSERT INTO EDITION_CONTRIBUTORS VALUES({self.id}, {contrib.id});")
        for tag_name in self.tag_names:
            tag = TAG.with_name(tag_name)
            sql.append(f"INSERT INTO EDITION_TAGS VALUES({self.id}, {tag.id});")
        for content_name in self.content_names:
            content_id = CONTENT.with_name(content_name).id
            sql.append(f"INSERT INTO EDITION_CONTENT VALUES({self.id}, {content_id});")

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
