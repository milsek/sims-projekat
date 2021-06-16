import tables
import requests

req = requests.session()
url = 'https://www.googleapis.com/books/v1/volumes'


def send_request(params):
    response = req.get(url=url, params=params)

    if response.status_code == 200:
        print("[200] Gat sam buks")
    else:
        print(f"[{response.status_code}] No buks tudej :(")
        return None

    return response.json()['items']


params = {'q': 'cryptography'}
items = send_request(params)
for item in items:
    info = item['volumeInfo']
    tables.EDITION(info)

print(len(tables.TAG.instances))

for tag in tables.TAG.instances:
    print(tag.toSql())
for content in tables.CONTENT.instances:
    print(content.toSql())
for person in tables.PERSON.instances:
    print("\n".join(person.toSql()))
for publisher in tables.PUBLISHER.instances:
    print(publisher.toSql())
for edition in tables.EDITION.instances:
    print("\n".join(edition.toSql()))

