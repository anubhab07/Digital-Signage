import datapackage
import pandas as pd

data_url = 'https://datahub.io/core/world-cities/datapackage.json'

# to load Data Package into storage
package = datapackage.Package(data_url)

# to load only tabular data
resources = package.resources
df = None
for resource in resources:
    if resource.tabular:
        df = pd.read_csv(resource.descriptor['path'])
        print(df.shape)
        print(df.head(5))
        break

df.to_csv("cities.csv", index=False)