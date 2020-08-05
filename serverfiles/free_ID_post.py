from firebase import firebase
import string
import random
import os
import time
def get_random_alphaNumeric_string(stringLength=10):
    lettersAndDigits = string.ascii_lowercase + string.digits
    return ''.join((random.choice(lettersAndDigits) for i in range(stringLength)))


def get_random_alphaNumeric_pwd(stringLength=13):
    lettersAndDigits = string.ascii_letters + string.digits
    return ''.join((random.choice(lettersAndDigits) for i in range(stringLength)))


firebase = firebase.FirebaseApplication(
    "your/firebase/app/link", None)
for i in range(100):
    mail_adresi = get_random_alphaNumeric_string(13)
    mail_pwd = get_random_alphaNumeric_pwd(10)
    print(mail_adresi+"  "+mail_pwd)
    mail = {
        'Mail_Address': mail_adresi,
        'Mail_Pwd': mail_pwd
    }
    res = firebase.post('/Mail_ID/free/', mail)
    print(res)
    os.system(f"/bin/bash ./curl_command.sh {mail_adresi}")
    time.sleep(2)
    #os.system(f"/bin/bash ./arg_test.sh {mail_adresi}"
    #print(mail_adresi)
