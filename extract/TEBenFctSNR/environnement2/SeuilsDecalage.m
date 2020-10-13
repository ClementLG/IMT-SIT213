
clear all
clc
CNRZT = importdata('C:\Users\clegruiec\OneDrive - RETIS\IMT\IMT-SIT213\extract\TEBenFctSNR\CNRZT-l100.txt',';',2);
NRZT = importdata('C:\Users\clegruiec\OneDrive - RETIS\IMT\IMT-SIT213\extract\TEBenFctSNR\NRZT-l100.txt',';',2);
datasCNRZT = CNRZT.data;
datasNRZT = NRZT.data;

ratio=datasCNRZT(:,1)./datasNRZT(:,1);

figure(1);
subplot(211);
plot(datasCNRZT(:,2:end),datasCNRZT(:,1),'b');
hold on
plot(datasNRZT(:,2:end),datasNRZT(:,1),'k');
hold on
plot(ratio,datasNRZT(:,1),'k');
hold on