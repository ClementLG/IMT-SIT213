clear all
clc
%Recuperation des DATA
RZ = importdata('C:\Users\clegruiec\OneDrive - RETIS\IMT\IMT-SIT213\extract\TEBenFctSNR\RZ.txt',';',2);
NRZ = importdata('C:\Users\clegruiec\OneDrive - RETIS\IMT\IMT-SIT213\extract\TEBenFctSNR\NRZ.txt',';',2);
NRZT = importdata('C:\Users\clegruiec\OneDrive - RETIS\IMT\IMT-SIT213\extract\TEBenFctSNR\NRZT.txt',';',2);
CRZ = importdata('C:\Users\clegruiec\OneDrive - RETIS\IMT\IMT-SIT213\extract\TEBenFctSNR\CRZ.txt',';',2);
CNRZ = importdata('C:\Users\clegruiec\OneDrive - RETIS\IMT\IMT-SIT213\extract\TEBenFctSNR\CNRZ.txt',';',2);
CNRZT = importdata('C:\Users\clegruiec\OneDrive - RETIS\IMT\IMT-SIT213\extract\TEBenFctSNR\CNRZT.txt',';',2);
datasRZ = RZ.data;
datasNRZ = NRZ.data;
datasNRZT = NRZT.data;
datasCRZ = CRZ.data;
datasCNRZ = CNRZ.data;
datasCNRZT = CNRZT.data;


%Affichahe temporelle
subplot(211);
plot(datasRZ(:,2:end),datasRZ(:,1),'b');
hold on
plot(datasCRZ(:,2:end),datasCRZ(:,1),'c');
hold on
plot(datasNRZ(:,2:end),datasNRZ(:,1),'Color' ,[0 0.5 0.5]);
hold on
plot(datasNRZ(:,2:end),datasCNRZ(:,1), 'g');
hold on
plot(datasNRZT(:,2:end),datasNRZT(:,1),'r');
hold on
plot(datasNRZ(:,2:end),datasCNRZT(:,1), 'm');
grid on
title('TEB en fonction du SNRpb - linear');
xlabel("SRNpb (dB)");
ylabel("TEB");
legend('RZ','CodRZ', ' NRZ','CodNRZ', 'NRZT','CodNRZT')

%Affichage log
figure(2)
semilogy(datasRZ(:,2),datasRZ(:,1),'b');
hold on
semilogy(datasRZ(:,2),datasCRZ(:,1),'c');
hold on
semilogy(datasNRZ(:,2),datasNRZ(:,1),'Color' ,[0 0.5 0.5]);
hold on
semilogy(datasRZ(:,2),datasCNRZ(:,1),'g');
hold on
semilogy(datasRZ(:,2),datasNRZT(:,1),'r');
hold on
semilogy(datasNRZT(:,2),datasCNRZT(:,1),'m');
axis([0 18 10^-6 0.6])
legend('RZ','CodRZ', ' NRZ','CodNRZ', 'NRZT','CodNRZT')
xlabel("SRNpb (dB)");
ylabel("TEB (log)");
title('TEB en fonction du SNRpb - Log');
grid on;