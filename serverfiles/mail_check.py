import time
from watchdog.observers import Observer
from watchdog.events import FileSystemEventHandler
import glob
import os
from pathlib import Path
import re


class Watcher:
    DIRECTORY_TO_WATCH = "/var/lib/docker/volumes/mailcowdockerized_vmail-vol-1/_data/tek-mail.net"

    def __init__(self):
        self.observer = Observer()

    def run(self):
        event_handler = Handler()
        self.observer.schedule(event_handler, self.DIRECTORY_TO_WATCH, recursive=True)
        self.observer.start()
        try:
            while True:
                time.sleep(5)
        except:
            self.observer.stop()
            print ("Error")

        self.observer.join()


class Handler(FileSystemEventHandler):
    @staticmethod
    def on_any_event(event):
        if event.is_directory:
            return None

        elif event.event_type == 'created':
            # Take any action here when a file is first created.           
            file_path=event.src_path
            head, sep, tail = file_path.partition("Maildir")     
            #print(head)
            #os.system(f"./entrypoint.sh {head}")
            #print("***************************************************")
            result =re.search("/var/lib/docker/volumes/mailcowdockerized_vmail-vol-1/_data/YOUR_DOMAIN/(.*)/Maildir", file_path)
            #print(result.group(1))
            user_name=result.group(1)
            print("Maili alan kullanıcı ---->")
            print(user_name)
            #mail_path=head+"Maildir/new"
            #print(mail_path)
            #os.system(f"docker-compose exec dovecot-mailcow /bin/bash ./sifre_cozucu.sh {user_name},{mail_path}")
            os.system(f"docker-compose exec -T dovecot-mailcow /bin/bash ./enc.sh {user_name}")

        #elif event.event_type == 'modified':
            # Taken any action here when a file is modified.
            #print ("Received modified event - %s." % event.src_path)


if __name__ == '__main__':
    ignore_directories = False
    w = Watcher()
    w.run()
