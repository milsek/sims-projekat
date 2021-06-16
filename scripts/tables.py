import random
import requests


class EDITION:
    instances = []

    def __init__(self, info):
        valid = False

        author_name = info.get('authors')[0]
        contributor_names = info.get('authors')[1:]
        publisher_name = info.get('publisher')

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
        if not self.rating:
            self.rating = random.randint(10, 50) / 10
        if self.year:
            self.year = info.get('publishedDate')[:4]
        else:
            self.year = '2021'
        if self.description:
            self.description = self.description.replace("'", "''")

        # TODO: Book type class, author id, publisher id, content, contributors, tags

    def toSql(self):
        return None


