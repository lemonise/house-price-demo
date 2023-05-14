# -*- coding: utf-8 -*-
"""predict_from_json.ipynb

Automatically generated by Colaboratory.

Original file is located at
    https://colab.research.google.com/drive/1KbuGHur1MJtONWLp5kZLyuSUi30XESMC
"""

import pickle
import json
import sys
from pathlib import Path
import pandas as pd

absolute_path = str(Path(__file__).parent.absolute())

model_filename = absolute_path + "\\" + "trained_model.pkl"
scaler_filename = absolute_path + "\\" + "scaler.pkl"
encoder_filename = absolute_path + "\\" + "encoder.pkl"

with open(model_filename, 'rb') as file:
  model = pickle.load(file)

with open(scaler_filename, 'rb') as file:
  scaler = pickle.load(file)

with open(encoder_filename, 'rb') as file:
  encoder = pickle.load(file)

print(sys.argv[1])

data = json.loads(sys.argv[1])

data = pd.DataFrame(data)

data['AreaCode'] = encoder.transform(data['AreaCode'])

data = scaler.transform(data)

predicted_val = str(model.predict(data)[0])

sys.stdout.write(predicted_val)
sys.stdout.flush()
sys.exit(0)