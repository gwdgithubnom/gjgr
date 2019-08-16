from numpy.random import randn
import numpy as np
np.random.seed(123)
import os
import matplotlib.pyplot as plt
import pandas as pd
plt.rc('figure', figsize=(10, 6))
np.set_printoptions(precision=4)
pd.options.display.max_rows = 20

fec = pd.read_csv('datasets/fec/P00000001-ALL.part0.csv')
part_files_index = [1,2,3]
part_files = ['datasets/fec/P00000001-ALL.part'+str(i)+'.csv' for i in part_files_index]
print(part_files)
part_files_data = [pd.read_csv(path,low_memory=False) for path in part_files]
#print([ff.info for ff in part_files_data])
for cache in part_files_data:
    fec = pd.concat([fec,cache],axis=0,ignore_index=True)
# fec = pd.concat([fec,cache],axis=0,ignore_index=True) for cache in files_data
fec.to_csv('datasets/fec/test.csv')
fec.info()