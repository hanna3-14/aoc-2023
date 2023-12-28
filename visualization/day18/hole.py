import matplotlib.pyplot as plt
import re

xlist = []
ylist = []

xEdge = []
yEdge = []

with open("interior.txt") as interior, open("edge.txt") as edge:
	liste = interior.read().split("), (")
	edgeListe = edge.read().split("), (")
	for i in range (0, len(liste)):
		liste[i] = re.sub('[\[\(\)\]]','', liste[i])
		y, x = liste[i].split(", ")
		xlist.append(int(x))
		ylist.append(-int(y))

	for i in range (0, len(edgeListe)):
		edgeListe[i] = re.sub('[\[\(\)\]]','', edgeListe[i])
		y, x = edgeListe[i].split(", ")
		xEdge.append(int(x))
		yEdge.append(-int(y))

		
	plt.scatter(xlist, ylist)
	plt.scatter(xEdge, yEdge, color='red')
	plt.savefig('day18_part1.png')
