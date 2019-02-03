# -*- coding: utf-8 -*-
"""
Created on Sat Feb  2 12:44:27 2019

@author: Jayant
"""
"""
<script src="https://www.gstatic.com/firebasejs/5.8.2/firebase.js"></script>
<script>
  // Initialize Firebase
  var config = {
    apiKey: "AIzaSyChR1zw9NGrwMuD5Flm6WgVxScvcmv70F0",
    authDomain: "testrun-a51b2.firebaseapp.com",
    databaseURL: "https://testrun-a51b2.firebaseio.com",
    projectId: "testrun-a51b2",
    storageBucket: "testrun-a51b2.appspot.com",
    messagingSenderId: "373762290703"
  };
  firebase.initializeApp(config);
</script>

6mPA5yIz2updbRv4Y5VkjFqF6uqSGn2Qiy5IgR0n

"""

import pyrebase
config = {
    "apiKey": "AIzaSyAob6vyddRr06ioOyoRit-h0V11tcsxYSc",
    "authDomain": "newsfacter.firebaseapp.com",
    "databaseURL": "https://newsfacter.firebaseio.com",
    "storageBucket": "newsfacter.appspot.com",}
firebase = pyrebase.initialize_app(config)

# =============================================================================
# from firebase import firebase
# firebase = firebase.FirebaseApplication('https://testrun-a51b2.firebaseio.com', authentication=None)
# result = firebase.get('/users', None)
# print (result)
# =============================================================================

# =============================================================================
# auth = firebase.auth()
# 
# # Log the user in
# user = auth.sign_in_with_email_and_password("jayantrane811@gmail.com", "6mPA5yIz2updbRv4Y5VkjFqF6uqSGn2Qiy5IgR0n")
# 
# # Get a reference to the database service
# db = firebase.database()
# 
# =============================================================================
import argparse
import numpy as np
from nltk.tokenize import sent_tokenize, word_tokenize
from nltk.corpus import stopwords
from string import punctuation
from nltk.probability import FreqDist
from heapq import nlargest
from collections import defaultdict


from googlesearch import search 
import urllib.request

import json
import pandas as pd
from twython import Twython  



def read_file(filename):
  fh = open(filename, "r")
  try:
      return fh.read()
  finally:
      fh.close()
      
    
def sanitize_input(data):
    replace = {
        ord('\f') : ' ',
        ord('\t') : ' ',
        ord('\n') : ' ',
        ord('\r') : None
    }

    return data.translate(replace)


def tokenize_content(content):
    stop_words = set(stopwords.words('english') + list(punctuation))
    words = word_tokenize(content.lower())
    
    return [
        sent_tokenize(content),
        [word for word in words if word not in stop_words]    
    ]

def score_tokens(filterd_words, sentence_tokens):
    word_freq = FreqDist(filterd_words)
    
    ranking = defaultdict(int)
    freq=0
    for i, sentence in enumerate(sentence_tokens):
        for word in word_tokenize(sentence.lower()):
            if word in word_freq:
                ranking[i] += word_freq[word]
                if(word_freq[word]>freq):
                  w=word
                  freq=word_freq[word]
                  
                
    return [ranking,w]

def summarize(ranks, sentences, length):
    if int(length) > len(sentences): 
        print("Error, more sentences requested than available. Use --l (--length) flag to adjust.")
        exit()

    indexes = nlargest(length, ranks, key=ranks.get)
    final_sentences = [sentences[j] for j in sorted(indexes)]
    return ' '.join(final_sentences) 
    #print(' '.join(final_sentences))


db = firebase.database()
db.child("users")
users = db.get()
print(users.val())


def main(): 

    #////SUMMERIZER///
    content = read_file('texttt.txt')
    content = sanitize_input(content)

    sentence_tokens, word_tokens = tokenize_content(content)  
    sentence_ranks,w = score_tokens(word_tokens, sentence_tokens)
    query=summarize(sentence_ranks, sentence_tokens,1)
    
    #////TWITTER///
    credentials = {}  
    credentials['CONSUMER_KEY']="4zJz3tkpAH2pyLHVY0E4bZmFa" 
    credentials['CONSUMER_SECRET'] = "L8VF6fAYtfl5ouryAgSWfYzq0DnNdo0qdG81zS47XtSWKCHiXt"
    credentials['ACCESS_TOKEN'] = "1423242756-RW3sdr0CtHvQP59WDAXAQoHIOUvAPaiWvjE19m1"
    credentials['ACCESS_SECRET'] = "qSgmYthkhB569jdugkT4wV0ulNwE0CesOYGjVWfw0KrB8"

    python_tweets = Twython(credentials['CONSUMER_KEY'], credentials['CONSUMER_SECRET']) 
    domain_list = ["com","org","co.in","in","edu","gov","uk","net"]  
    for link in search(query, tld="com", tpe="nws", num=2, stop=1, pause=2):
        with urllib.request.urlopen("http://www.python.org") as link:
            #myfile = link.read()
          print(link)
        #print(myfile)

    query = {'q': w,  
        'result_type': 'popular',
        'count': 5,
        'lang': 'en',
        }

    dict_ = {'user': [], 'date': [], 'text': [], 'favorite_count': []}  
    for status in python_tweets.search(**query)['statuses']:  
        dict_['user'].append(status['user']['screen_name'])
        dict_['date'].append(status['created_at'])
        dict_['text'].append(status['text'])
        dict_['favorite_count'].append(status['favorite_count'])

   
     
    for i in dict_['text']:
      print(i)
  
if __name__ == "__main__":
    main()

