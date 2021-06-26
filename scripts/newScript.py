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


run_api({'q': 'cryptography', 'maxResults': 10}, ['cryptography', 'security', 'computers', 'communications'])
run_api({'q': 'python', 'maxResults': 10}, ['programming', 'python', 'computers'])
run_api({'q': 'c++', 'maxResults': 10}, ['programming', 'c++', 'computers'])
# run_api({'q': 'security', 'maxResults': 30}, ['programming', 'hacking', 'security', 'cryptography', 'computers'])
# run_api({'q': 'java', 'maxResults': 30}, ['programming', 'java', 'computers'])
# run_api({'q': 'artificial intelligence', 'maxResults': 30}, ['machine-learning', 'ai', 'computers'])
# run_api({'q': 'django', 'maxResults': 30}, ['programming', 'web-development', 'python'])
# run_api({'q': 'guitar', 'maxResults': 30}, ['guitar', 'music', 'art'])
# run_api({'q': 'linux', 'maxResults': 30}, ['os', 'security', 'programming', 'computers'])
# run_api({'q': 'database', 'maxResults': 30}, ['database', 'sql', 'web-development', 'data'])


file = open('data.sql', 'w')

for tag in tables.TAG.instances:
    file.write(tag.toSql() + '\n')
for content in tables.CONTENT.instances:
    file.write(content.toSql() + '\n')
for contributor in tables.CONTRIBUTOR.instances:
    file.write(contributor.toSql() + '\n')
for contribution in tables.CONTRIBUTION.instances:
    file.write(contribution.toSql() + '\n')
for publisher in tables.PUBLISHER.instances:
    file.write(publisher.toSql() + '\n')
for edition in tables.EDITION.instances:
    file.write('\n'.join(edition.toSql()) + '\n')

file.close()
