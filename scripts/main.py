import requests
import json
import urllib.request
from model import *

req = requests.session()
url = 'https://www.googleapis.com/books/v1/volumes'

editions = []


def send_request(params):
    response = req.get(url=url, params=params)

    if response.status_code == 200:
        print(f"Gat sam buks")
    else:
        print(f"Error!")
        return

    data = json.loads(response.text)
    for item in data['items']:
        volume_info = item['volumeInfo']
        ed = Edition(volume_info)

        if not ed.imageLarge:
            continue

        editions.append(ed)

        # urllib.request.urlretrieve(ed.image_link, f'./downloads/{len(editions) - 1}.jpeg')


def get_from_author(author):
    params = {'q': f'inauthor:{author}',
              'maxResults': 10,
              'orderBy': 'relevance',
              'langRestrict': 'en'}
    send_request(params)


def get_by_keyword(keyword):
    params = {'q': f'{keyword}',
              'maxResults': 10,
              'orderBy': 'relevance'}
    send_request(params)


authors = ['sigmund+freud', 'friedrich+nietzche', 'immanuel+kant', 'karl+marx', 'leo+tolstoy', 'fyodor+dostoevsky',
           'alexander+pushkin', 'charles+baudelaire',
           'victor+hugo', 'charles+dickens', 'george+orwell', 'hermann+hesse', 'arthur+rimbaud', 'ernest+hemingway',
           'mark+twain', 'j+k+rowling', 'agatha+christie',
           'franz+kafka', 'thomas+mann', 'jean+paul+sartre', 'jordan+peterson', 'ivo+andric', 'mesa+selimovic',
           'milos+crnjanski', 'desanka+maksimovic',
           'danilo+kis']

keywords = ['cryptography', 'java', 'python']

add("INSERT INTO CONTRIBUTOR_TYPE VALUES (0, 'Test');")
for k in keywords:
    get_by_keyword(k)

# path = "./output/"

# booktypejson = open(path + "booktype.json", "w")
# contentjson = open(path + "content.json", "w")
# tagjson = open(path + "tag.json", "w")
# publisherjson = open(path + "publisher.json", "w")
# personjson = open(path + "person.json", "w")
# authorjson = open(path + "author.json", "w")
# contributorjson = open(path + "contributor.json", "w")
# editionsjson = open(path + "editions.json", "w")
#
# # booktypejson.write(json.dumps(BookType.entities, default=lambda x: x.__dict__, indent=4))
# contentjson.write(json.dumps(list(Content.map.values()), default=lambda x: x.__dict__, indent=4))
# tagjson.write(json.dumps(list(Tag.map.values()), default=lambda x: x.__dict__, indent=4))
# publisherjson.write(json.dumps(list(Publisher.map.values()), default=lambda x: x.__dict__, indent=4))
# personjson.write(json.dumps(list(Person.map.values()), default=lambda x: x.__dict__, indent=4))
# authorjson.write(json.dumps(Author.entities, default=lambda x: x.__dict__, indent=4))
# contributorjson.write(json.dumps(Contributor.entities, default=lambda x: x.__dict__, indent=4))
# editionsjson.write(json.dumps(editions, default=lambda x: x.__dict__, indent=4))
#
# # booktypejson.close()
# contentjson.close()
# tagjson.close()
# publisherjson.close()
# personjson.close()
# authorjson.close()
# contributorjson.close()
# editionsjson.close()

file = open("inserts.sql", "w")
file.write("\n".join(queries))
file.close()