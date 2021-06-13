import random
import requests

imgchecker = requests.session()

queries = []


def add(query):
    queries.append(query)


class BookType:
    entities = []

    def __init__(self, info):
        self.id = len(BookType.entities)
        self.availableCopies = random.randint(5, 60)
        self.title = info.get('title').replace("'", r"''")

        BookType.entities.append(self)
        add(f"INSERT INTO BOOK_TYPE VALUES ({self.id}, {self.availableCopies}, '{self.title}');")


class Content:
    map = {}

    def __init__(self, name):
        self.id = len(Content.map)
        self.name = name
        self.editionSet = []

        add(f"INSERT INTO CONTENT VALUES ({self.id}, '{self.name}');")

    def addEdition(self, edition):
        self.editionSet.append(edition.id)

    @staticmethod
    def getByName(name):
        instance = Content.map.get(name)
        if not instance:
            instance = Content(name)
            Content.map[name] = instance
        return instance


class Tag:
    map = {}

    def __init__(self, name):
        self.id = len(Tag.map)
        self.name = name
        self.editionSet = []

        add(f"INSERT INTO TAG VALUES ({self.id}, '{self.name}');")

    def addEdition(self, edition):
        self.editionSet.append(edition.id)

    @staticmethod
    def getByName(name):
        instance = Tag.map.get(name)
        if not instance:
            instance = Tag(name)
            Tag.map[name] = instance
        return instance


class Publisher:
    map = {}

    def __init__(self, name):
        self.id = len(Publisher.map)
        self.name = name
        self.editionSet = []

        add(f"INSERT INTO PUBLISHER VALUES ({self.id}, '{self.name}');")

    def addEdition(self, edition):
        self.editionSet.append(edition.id)

    @staticmethod
    def getByName(name):
        instance = Publisher.map.get(name)
        if not instance:
            instance = Publisher(name)
            Publisher.map[name] = instance
        return instance


class Person:
    map = {}

    def __init__(self, name):
        self.id = len(Person.map)
        self.name = name

        add(f"INSERT INTO PERSON VALUES({self.id}, '{self.name}');")


class Author(Person):
    entities = []

    def __init__(self, name):
        super().__init__(name)
        self.editionSet = []

        Author.entities.append(self)
        add(f"INSERT INTO AUTHOR VALUES({self.id});")

    def addEdition(self, edition):
        self.editionSet.append(edition.id)

    @staticmethod
    def getByName(name):
        instance = Person.map.get(name)
        if not instance:
            instance = Author(name)
            Person.map[name] = instance
        return instance


class Contributor(Person):
    entities = []

    def __init__(self, name):
        super().__init__(name)
        self.editionSet = []

        Contributor.entities.append(self)
        add(f"INSERT INTO CONTRIBUTOR VALUES ({self.id}, 0);")

    def addEdition(self, edition):
        self.editionSet.append(edition.id)

    @staticmethod
    def getByName(name):
        instance = Person.map.get(name)
        if not instance:
            instance = Contributor(name)
            Person.map[name] = instance
        return instance


class Edition(BookType):
    def __init__(self, info):
        self.takeOut = random.choice(['true', 'true', 'true', 'true', 'false'])
        self.reads = random.randint(5, 500)
        if info.get('rating'):
            self.rating = info.get('rating')
        else:
            self.rating = random.randint(10, 50) / 10
        self.dimensions = random.randint(18, 22)

        self.pageCount = info.get('pageCount')
        self.description = info.get('description')
        if self.description:
            self.description = self.description.replace("'", r"''")

        self.language = info.get('language')
        if info.get('publishedDate'):
            self.year = info.get('publishedDate')[:4]
        else:
            self.year = '1900'

        if info.get('imageLinks'):
            self.imageSmall = info.get('imageLinks').get('thumbnail').replace('edge=curl&', '')
            self.imageLarge = info.get('imageLinks').get('thumbnail').replace('edge=curl&', '').replace('zoom=1&', '')

            if len(imgchecker.get(self.imageLarge).content) == 9103:
                self.imageLarge = None
                return

        else:
            self.thumbnail = None

        super().__init__(info)

        self.contents = []
        if not info.get('categories'):
            self.imageLarge = None
            return
        for content_name in info.get('categories'):
            content = Content.getByName(content_name)
            self.contents.append(content.id)
            content.addEdition(self)

        self.tags = []
        for tag_name in ['tagovi', 'idu', 'ovde']:
            tag = Tag.getByName(tag_name)
            self.tags.append(tag.id)
            tag.addEdition(self)

        publisher_name = info.get('publisher')
        if publisher_name:
            publisher_name = publisher_name.replace("'", r"''")
        publisher = Publisher.getByName(publisher_name)
        self.publisher = publisher.id
        publisher.addEdition(self)

        author_name = info.get('authors')[0]
        author = Author.getByName(author_name)
        self.author = author.id
        author.addEdition(self)

        self.contributors = []
        if len(info.get('authors')) > 1:
            for contributor_name in info.get('authors')[1:]:
                contrib = Contributor.getByName(contributor_name)
                self.contributors.append(contrib.id)
                contrib.addEdition(self)

        add(f"INSERT INTO EDITION VALUES ('{self.description[:200]}', '{self.dimensions}', "
            f"'{self.imageLarge}', '{self.imageSmall}', '{self.language}', {self.pageCount}, "
            f"{self.rating}, {self.reads}, {self.takeOut}, PARSEDATETIME('{self.year}', 'yyyy'), {self.id}, {self.author}, {self.publisher});")

        for tag in self.tags:
            add(f"INSERT INTO EDITION_TAGS VALUES({self.id}, {tag});")
        for contrib in self.contributors:
            add(f"INSERT INTO EDITION_CONTRIBUTORS VALUES({self.id}, {contrib});")
        for content in self.contents:
            add(f"INSERT INTO EDITION_CONTENT VALUES({self.id}, {content});")