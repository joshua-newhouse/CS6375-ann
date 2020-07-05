# CS6375-ann

### Artificial neutal network program

Used the following dataset as input
https://archive.ics.uci.edu/ml/machine-learning-databases/adult/adult.data

To run provide the absolute filepath to the adult.data file as the first and only command line argument.

#### Attribute Information:

Listing of attributes:

age: continuous.

workclass: Private, Self-emp-not-inc, Self-emp-inc, Federal-gov, Local-gov, State-gov, Without-pay, Never-worked.

fnlwgt: continuous.

education: Bachelors, Some-college, 11th, HS-grad, Prof-school, Assoc-acdm, Assoc-voc, 9th, 7th-8th, 12th, Masters, 1st-4th, 10th, Doctorate, 5th-6th, Preschool.

education-num: continuous.

marital-status: Married-civ-spouse, Divorced, Never-married, Separated, Widowed, Married-spouse-absent, Married-AF-spouse.

occupation: Tech-support, Craft-repair, Other-service, Sales, Exec-managerial, Prof-specialty, Handlers-cleaners, Machine-op-inspct, Adm-clerical, Farming-fishing, Transport-moving, Priv-house-serv, Protective-serv, Armed-Forces.

relationship: Wife, Own-child, Husband, Not-in-family, Other-relative, Unmarried.

race: White, Asian-Pac-Islander, Amer-Indian-Eskimo, Other, Black.

sex: Female, Male.

capital-gain: continuous.

capital-loss: continuous.

hours-per-week: continuous.

native-country: United-States, Cambodia, England, Puerto-Rico, Canada, Germany, Outlying-US(Guam-USVI-etc), India, Japan, Greece, South, China, Cuba, Iran, Honduras, Philippines, Italy, Poland, Jamaica, Vietnam, Mexico, Portugal, Ireland, France, Dominican-Republic, Laos, Ecuador, Taiwan, Haiti, Columbia, Hungary, Guatemala, Nicaragua, Scotland, Thailand, Yugoslavia, El-Salvador, Trinadad&Tobago, Peru, Hong, Holand-Netherlands.

### Program Execution

- Executing Gradient Descent without using any library to calculate Mean Squared Error for Train and test data

		python Linear_Regression_Model.py >> output.txt

- Above program outputs two plots with below file names in the same project folder 
along with output txt file which records ***learning rate***, ***Number of iterations***, ***Train MSE***, ***Test MSE***

		MSE_vs_Iterations_LearningRate0.1.png
		MSE_vs_Iterations_LearningRate0.01.png
		output.txt

- Executing Gradient Descent using sklearn library

		python SkiLearn_Regression_Model.py >> output1.txt

- Above program outputs below file names in the same project folder which records ***weights caluclated***, ***Mean Squared Error***

		output1.txt


