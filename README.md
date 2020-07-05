# CS6375-ann

### Artificial neutal network program

Used the following dataset as input
https://archive.ics.uci.edu/ml/machine-learning-databases/adult/adult.data

Hosted this file in AWS S3

Bucket Name : ***cs6375-vxs190040***

File Name: ***adult.data***

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

- Executing Neural Network with inputs

		 java -jar ccs6375-ann-1.0.jar --activation-function tanh --alpha 0.5 --iterations 10000 --weight-upper-bound 5.0 --weight-lower-bound -5.0 --layer-width 5 --hidden-layers 2

- Executing Neural Network without inputs

		 java -jar cs6375-ann-1.0.jar

- Above program inputs arguments ***activation function***, ***Learning Rate (alpha)***, ***Number of iterations***, ***Weights Lower & Upper bounds***

		activation function possible inputs : sigmoid, relu, tanh

- Default inputs if no inputs provided

		activation function: sigmoid
		weight upper & lower bound : 20.0 & -20.0
		iteration: 1000
		learning rate (alpha): 1.0
		hidden layers: 2
		layer width: 5

- Sample output for this program

		Testing Data Correct predictions: 300
		Total predictions: 400
		Accuracy: 0.75000

		Training Data Correct predictions: 100
		Total predictions: 120
		Accuracy: 0.833333




