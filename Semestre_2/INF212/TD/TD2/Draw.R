# pour executer le code : R CMD BATCH "Draw.R"

modelname = "Simulation_MM1.data"
data = read.table(modelname)
attach(data);

temps = V1
Moyenne = V2

plot(temps,Moyenne,type="l",xlab="ite",ylab="perti",col="red")
