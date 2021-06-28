import tables
import requests
import random

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


run_api({'q': 'cryptography', 'maxResults': 10}, [
        'cryptography', 'security', 'computers', 'communications'])
# run_api({'q': 'python', 'maxResults': 10}, ['programming', 'python', 'computers'])
# run_api({'q': 'c++', 'maxResults': 10}, ['programming', 'c++', 'computers'])
# run_api({'q': 'security', 'maxResults': 30}, ['programming', 'hacking', 'security', 'cryptography', 'computers'])
# run_api({'q': 'java', 'maxResults': 30}, ['programming', 'java', 'computers'])
# run_api({'q': 'artificial intelligence', 'maxResults': 30}, ['machine-learning', 'ai', 'computers'])
# run_api({'q': 'django', 'maxResults': 30}, ['programming', 'web-development', 'python', 'django'])
# run_api({'q': 'linux', 'maxResults': 30}, ['os', 'security', 'programming', 'computers'])
# run_api({'q': 'database', 'maxResults': 30}, ['database', 'sql', 'web-development', 'data'])

streets = ['Teše Tešanovića', 'Developerska', '11. aprila', 'Satošijeva', 'Dajkstrina', 'Inženjerska', 'Trg Gvida van Rosuma', 'Kanjea Zapadnog', 'Ozrena Soldatovića',
         'Hari Poterova', 'Lignjoslavljeva', 'Rokija Balboe', 'Indijane Džonsa', 'Sina Dragana', 'Žrtava junskog roka', 'Stefana Đurića Raste', 'Žrtava junskog roka']
sections = ['A', 'B', 'C', 'D'] # TODO: Smisli

tables.PLACE('Novi Sad', 21000)
tables.PLACE('Zrenjanin', 23000)

for place in tables.PLACE.instances:
    tables.BUILDING('Main library', random.choice(streets), random.randint(10, 30), place)
    tables.BUILDING('Library II', random.choice(streets), random.randint(10, 30), place)
    tables.BUILDING('Library III', random.choice(streets), random.randint(10, 30), place)

for building in tables.BUILDING.instances:
    for name in sections:
        tables.SECTION(name, building)

for section in tables.SECTION.instances:
    for i in range(random.randint(0,4)):
        tables.ISLE(chr(65+i), section) # Isles with names A, B, C...

for isle in tables.ISLE.instances:
    for i in range(random.randint(4, 8)):
        tables.LINE(i, isle)


file = open('data.sql', 'w')

for place in tables.PLACE.instances:
    file.write(place.toSql() + '\n')
for building in tables.BUILDING.instances:
    file.write(building.toSql() + '\n')
for section in tables.SECTION.instances:
    file.write(section.toSql() + '\n')
for isle in tables.ISLE.instances:
    file.write(isle.toSql() + '\n')
for line in tables.LINE.instances:
    file.write(line.toSql() + '\n')

for tag in tables.TAG.instances:
    file.write(tag.toSql() + '\n')
for genre in tables.GENRE.instances:
    file.write(genre.toSql() + '\n')
for contributor in tables.CONTRIBUTOR.instances:
    file.write(contributor.toSql() + '\n')
for contribution in tables.CONTRIBUTION.instances:
    file.write(contribution.toSql() + '\n')
for publisher in tables.PUBLISHER.instances:
    file.write(publisher.toSql() + '\n')
for edition in tables.EDITION.instances:
    file.write('\n'.join(edition.toSql()) + '\n')
for book in tables.BOOK.instances:
    file.write(book.toSql() + '\n')

file.close()
