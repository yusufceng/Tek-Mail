#!/usr/bin/python

from selenium import webdriver
from selenium.webdriver.chrome.options import Options
import time
from selenium.common.exceptions import NoSuchElementException
from selenium.webdriver.common.keys import Keys
import random
import string

chrome_options = Options()
chrome_options.add_argument("--headless")
chrome_options.add_argument('--no-sandbox')


driver_path = "/home/ubuntu/chromedriver"
browser=webdriver.Chrome(driver_path)

browser.get("http://tek-mail.net/")
time.sleep(1)
admin_uid="admin"
admin_pwd="moohoo"
adres_alani=browser.find_element_by_name("login_user")
adres_alani.send_keys(admin_uid)
sifre_alani=browser.find_element_by_name("pass_user")
sifre_alani.send_keys(admin_pwd)
#//*[@id='top']/div[2]/div/div[1]/div/div[2]/form/div[3]/button
giris_yap=browser.find_element_by_xpath("//*[@id='top']/div[2]/div/div[1]/div/div[2]/form/div[3]/button")
giris_yap.click()
time.sleep(2)
browser.get("http://tek-mail.net/mailbox")
time.sleep(1)
browser.find_element_by_xpath("//*[@id='top']/div[2]/ul/li[2]/a").click()
time.sleep(1)
browser.find_element_by_xpath("//*[@id='tab-mailboxes']/div/div[3]/div/a[3]").click()
time.sleep(2)





domain="@tek-mail.net"

def get_random_alphaNumeric_string(stringLength=10):
    lettersAndDigits = string.ascii_lowercase + string.digits
    return ''.join((random.choice(lettersAndDigits) for i in range(stringLength)))

def get_random_alphaNumeric_pwd(stringLength=13):
    lettersAndDigits = string.ascii_letters + string.digits
    return ''.join((random.choice(lettersAndDigits) for i in range(stringLength)))

mail_adresi=get_random_alphaNumeric_string(13)
mail_pwd=get_random_alphaNumeric_pwd(10)
print(mail_adresi+"  "+mail_pwd)

user_name_alani=browser.find_element_by_name("local_part")
user_name_alani.send_keys(mail_adresi)

name_alani=browser.find_element_by_xpath("//*[@id='addMailboxModal']/div/div/div[2]/form/div[3]/div/input")
name_alani.send_keys(mail_adresi)

password_alani=browser.find_element_by_name("password")
password_alani.send_keys(mail_pwd)

password_alani2=browser.find_element_by_name("password2")
password_alani2.send_keys(mail_pwd)

mailbox_size=browser.find_element_by_name("quota")
mailbox_size.send_keys(Keys.CONTROL+ "a")
mailbox_size.send_keys(Keys.DELETE)
time.sleep(1)
mailbox_size.send_keys("256")



mail_adresi=get_random_alphaNumeric_string(13)
mail_pwd=get_random_alphaNumeric_pwd(10)
print(mail_adresi+"  "+mail_pwd)
time.sleep(1)
btn_ekle=browser.find_element_by_xpath("//*[@id='addMailboxModal']/div/div/div[2]/form/div[8]/div/button").click()



