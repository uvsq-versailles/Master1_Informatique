# pour executer le code : R CMD BATCH "Draw.R"

modelname = "Test.data"
data = read.table(modelname)
attach(data);

iteration = V1
pertincence = V2

plot(temps,Moyenne,type="l",xlab="ite",ylab="perti",col="red")
