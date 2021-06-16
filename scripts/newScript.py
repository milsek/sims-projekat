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


def run_api(params, tags):
    items = send_request(params)
    for item in items:
        info = item['volumeInfo']
        tables.EDITION(info, tags)


run_api({'q': 'cryptography'}, ['cryptography', 'security', 'computers', 'communications'])
run_api({'q': 'python'}, ['programming', 'python', 'computers'])
run_api({'q': 'c++'}, ['programming', 'c++', 'computers'])
run_api({'q': 'security'}, ['programming', 'hacking', 'security', 'cryptography', 'computers'])
run_api({'q': 'java'}, ['programming', 'java', 'computers'])
run_api({'q': 'artificial intelligence'}, ['machine-learning', 'ai', 'computers'])
run_api({'q': 'django'}, ['programming', 'web-development', 'python'])

file = open('data.sql', 'w')

for tag in tables.TAG.instances:
    file.write(tag.toSql() + '\n')
for content in tables.CONTENT.instances:
    file.write(content.toSql() + '\n')
for person in tables.PERSON.instances:
    file.write('\n'.join(person.toSql()) + '\n')
for publisher in tables.PUBLISHER.instances:
    file.write(publisher.toSql() + '\n')
for edition in tables.EDITION.instances:
    file.write('\n'.join(edition.toSql()) + '\n')

file.close()
