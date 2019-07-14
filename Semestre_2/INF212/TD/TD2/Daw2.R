# pour executer le code : R CMD BATCH "Daw2.R"

modelname = "Simul_charge.data"
data = read.table(modelname)
attach(data);

charge = V1
Nmoyen = V2

plot(charge,Nmoyen,type="l",xlab="charge",ylab="Nmoyen",col="red")
