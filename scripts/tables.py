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
            GENRE.with_name(name)
        for name in self.tag_names:
            TAG.with_name(name)

        EDITION.instances.append(self)
        print(f"[+] Faund {len(EDITION.instances)} buks")

    def toSql(self):
        sql = [f"INSERT INTO BOOK_TITLE VALUES({self.id}, '{escape(self.title)}');",
               f"INSERT INTO EDITION VALUES({self.id}, {self.available_copies}, '{trim(escape(self.description))}', '{self.dimensions}', '{self.image_large}', "
               f"'{self.image_small}', '{self.language}', {self.page_count}, {self.rating}, {self.reads}, {self.take_out}, PARSEDATETIME('{self.year}', 'yyyy'), "
               f"{self.publisher_id}, {self.id});"]

        for name in self.author_names:
            contributor = CONTRIBUTOR.with_name(name)
            contribution = CONTRIBUTION.with_contrib_and_type(contributor, 'author')
            sql.append(f"INSERT INTO BOOK_TITLE_CONTRIBUTIONS VALUES({self.id}, {contribution.id});")
        for name in self.genre_names:
            genre = GENRE.with_name(name)
            sql.append(f"INSERT INTO BOOK_TITLE_GENRES VALUES({self.id}, {genre.id});")
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
        return f"INSERT INTO CONTRIBUTION VALUES({self.id}, '{self.ctype}', {self.contributor.id});"


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


class GENRE:
    instances = []
    map = {}

    def __init__(self, name):
        self.id = len(GENRE.instances)
        self.name = name

        GENRE.instances.append(self)
        GENRE.map[name] = self

    @staticmethod
    def with_name(name):
        if GENRE.map.get(name):
            return GENRE.map.get(name)
        else:
            return GENRE(name)

    def toSql(self):
        return f"INSERT INTO GENRE VALUES({self.id}, '{escape(self.name)}', null);"


class BOOK_RESERVATION:
    instances = []

    def __init__(self, book, date, reservation):
        self.id = reservation.id
        self.date = date
        self.book_id = book.id

        BOOK_RESERVATION.instances.append(self)

    def toSql(self):
        return f"INSERT INTO BOOK_RESERVATION VALUES(PARSEDATETIME('{self.date}', 'yyyy-mm-dd'), {self.id}, {self.book_id});"


class PICTURE_BOOK_RESRVATION:
    instances = []

    def __init__(self, reservation):
        self.id = reservation.id
        self.amount = random.randint(1,4)

        PICTURE_BOOK_RESRVATION.instances.append(self)

    def toSql(self):
        return f"INSERT INTO PICTURE_BOOK_RESERVATION VALUES({self.amount}, {self.id});"


class RESERVATION:
    instances = []

    def __init__(self, member):
        self.id = len(RESERVATION.instances)
        self.date_returned = '2021-01-01'               # TODO: Think of random generation mechanism
        self.date_taken = '2021-01-02'        # TODO: Do the same
        self.member_id = member.id
        self.state_name = 'new'               # TODO: Make something up

        RESERVATION.instances.append(self)

    def toSql(self):
        return f"INSERT INTO RESERVATION VALUES({self.id}, PARSEDATETIME('{self.date_returned}', 'yyyy-mm-dd'), PARSEDATETIME('{self.date_taken}', 'yyyy-mm-dd'), '{self.state_name}', {self.member_id});"



class MEMBER:
    instances = []

    def __init__(self, user):
        self.id = user.id

        MEMBER.instances.append(self)

    def toSql(self):
        return f"INSERT INTO MEMBER VALUES({self.id});"


class USER:
    instances = []

    def __init__(self, name, surname, birthdate, library, account):
        self.id = len(USER.instances)
        self.birthdate = birthdate
        self.name = name
        self.surname = surname
        self.account_id = account.id
        self.library_id = library.id

        USER.instances.append(self)

    def toSql(self):
        return f"INSERT INTO USER VALUES(0, {self.id}, PARSEDATETIME('{self.birthdate}', 'yyyy-mm-dd'), '{self.name}', '{self.surname}', {self.account_id}, {self.library_id});"


class ACCOUNT:
    instances = []

    def __init__(self, mail, library):
        self.id = len(ACCOUNT.instances)
        self.mail = mail
        self.password = 'password1'
        self.reference_number = str(self.id).zfill(10)
        self.library_id = library.id

        ACCOUNT.instances.append(self)

    def toSql(self):
        return f"INSERT INTO ACCOUNT VALUES({self.id}, '{self.mail}', '{self.password}', '{self.reference_number}', {self.library_id});"


class MEMBERSHIP:
    instances = []

    def __init__(self, start_date, end_date, price, user):
        self.id = len(MEMBERSHIP.instances)
        self.end = end_date # TODO: Generiši u prošlosti i u budućnosti
        self.start = start_date
        self.price_id = price.id
        self.user_id = user.id

        MEMBERSHIP.instances.append(self)
    
    def toSql(self):
        return f"INSERT INTO MEMBERSHIP VALUES({self.id}, PARSEDATETIME('{self.end}', 'yyyy-mm-dd'), PARSEDATETIME('{self.start}', 'yyyy-mm-dd'), {self.price_id}, {self.user_id});"        



class BOOK:
    instances = []

    def __init__(self, edition):
        self.id = len(BOOK.instances)
        self.condition = random.choice([0,0,0,1,1,1,1,2,2,3])
        self.state_name = 'inStock' # TODO: Other states
        self.edition_id = edition.id
        self.line_id = random.choice(LINE.instances).id

        BOOK.instances.append(self)

    def toSql(self):
        # TODO: Check order of values
        return f"INSERT INTO BOOK VALUES({self.id}, {self.condition}, '{escape(self.state_name)}', {self.edition_id}, {self.line_id});"


class LINE:   
    instances = []

    def __init__(self, number, isle):
        self.id = len(LINE.instances)
        self.number = number
        self.isle_id = isle.id
        
        LINE.instances.append(self)

    def toSql(self):
        return f"INSERT INTO LINE VALUES({self.id}, '{self.number}', {self.isle_id});"


class ISLE:
    instances = []

    def __init__(self, name, section):
        self.id = len(ISLE.instances)
        self.name = name
        self.section_id = section.id
        
        ISLE.instances.append(self)

    def toSql(self):
        return f"INSERT INTO ISLE VALUES({self.id}, '{self.name}', {self.section_id});"


class SECTION:
    instances = []

    def __init__(self, name, building):
        self.id = len(SECTION.instances)
        self.name = name
        self.building_id = building.id
        
        SECTION.instances.append(self)

    def toSql(self):
        return f"INSERT INTO SECTION VALUES({self.id}, '{self.name}', {self.building_id});"


class BUILDING:
    instances = []

    def __init__(self, name, address, num_of_pb, place, library):
        self.id = len(BUILDING.instances)
        self.name = name
        self.address = address
        self.num_of_pb = num_of_pb
        self.place_id = place.id
        self.library_id = library.id

        BUILDING.instances.append(self)

    def toSql(self):
        return f"INSERT INTO BUILDING VALUES({self.id}, '{self.address}', '{self.name}', {self.num_of_pb}, {self.place_id}, {self.library_id});"


class LIBRARY:
    instances = []

    def __init__(self, name):
        self.id = len(LIBRARY.instances)
        self.name = name

        LIBRARY.instances.append(self)

    def toSql(self):
        return f"INSERT INTO LIBRARY VALUES({self.id}, '{self.name}');"


class PLACE:
    instances = []

    def __init__(self, name, postal):
        self.id = len(PLACE.instances)
        self.name = name
        self.postal = postal

        PLACE.instances.append(self)

    def toSql(self):
        return f"INSERT INTO PLACE VALUES({self.id}, '{self.name}', {self.postal});"


class CATEGORY_RULES:
    instances = []

    def __init__(self, rule, num_books, num_days):
        self.id = len(CATEGORY_RULES.instances)
        self.rule = rule
        self.num_books = num_books
        self.num_days = num_days

        CATEGORY_RULES.instances.append(self)

    def toSql(self):
        return f"INSERT INTO CATEGORY_RULES VALUES({self.id}, {self.rule}, {self.num_books}, {self.num_days});"


class PRICE:
    instances = []

    def __init__(self, start_date, end_date, price, category):
        self.id = len(PRICE.instances)
        self.start = start_date
        self.end = end_date
        self.price = price
        self.category_id = category.id

        PRICE.instances.append(self)

    def toSql(self):
        return f"INSERT INTO PRICE VALUES({self.id}, PARSEDATETIME('{self.end}', 'yyyy-mm-dd'), {self.price}, PARSEDATETIME('{self.start}', 'yyyy-mm-dd'), {self.category_id});"


class LIBRARIAN:
    instances = []

    def __init__(self, user):
        self.id = user.id
        self.workplace = random.randint(1,3)

        LIBRARIAN.instances.append(self)

    def toSql(self):
        return f"INSERT INTO LIBRARIAN VALUES({self.workplace}, {self.id});"

class DAILY_TRANSACTION:
    instances = []

    def __init__(self, membership, librarian, library):
        self.id = len(DAILY_TRANSACTION.instances)
        self.date = membership.start
        self.librarian_id = librarian.id
        self.membership_id = membership.id
        self.library_id = library.id

        DAILY_TRANSACTION.instances.append(self)

    def toSql(self):
        return f"INSERT INTO DAILY_TRANSACTION VALUES({self.id}, PARSEDATETIME('{self.date}', 'yyyy-mm-dd'), {self.librarian_id}, {self.membership_id}, {self.library_id});"

def escape(string):
    return string.replace("'", "''")


def trim(string):
    return string[:900] + string[900:string.find(".", 900) + 1][:100]
