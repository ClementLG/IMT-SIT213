%java  Simulateur  -seed  40  -form  NRZ  -ampl  -5  5  -snrpb  -20
%java  Simulateur  -seed  40  -form  RZ  -ampl  -5  5  -snrpb  -20
%java  Simulateur  -seed  40  -form  NRZT  -ampl  -5  5  -snrpb  -20
%100K essaies

%mettre la source des données
% fid = fopen('C:\Users\clegruiec\OneDrive - RETIS\IMT\IMT-SIT213\extract\TEBenFctSNR\NRZT-l100.txt', 'r');
% T = fscanf(fid, '%f %f\n', [2 Inf]);
% histogram(T)
% fclose(fid);



%------------------------------------
%-seed 40 -form RZ -ampl -5 5 -snrpb "+i+" -export";
%-seed 40 -form NRZ -ampl -5 5 -snrpb "+i+" -export";
%-seed 40 -form NRZT -ampl -5 5 -snrpb "+i+" -export";

clear all
clc
RZ = importdata('C:\Users\clegruiec\OneDrive - RETIS\IMT\IMT-SIT213\extract\TEBenFctSNR\RZ-l100.txt',';',2);
NRZ = importdata('C:\Users\clegruiec\OneDrive - RETIS\IMT\IMT-SIT213\extract\TEBenFctSNR\NRZ-l100.txt',';',2);
NRZT = importdata('C:\Users\clegruiec\OneDrive - RETIS\IMT\IMT-SIT213\extract\TEBenFctSNR\NRZT-l100.txt',';',2);

datasRZ = RZ.data;
datasNRZ = NRZ.data;
datasNRZT = NRZT.data;

subplot(211);
plot(datasRZ(:,2:end),datasRZ(:,1),'b');
grid on
xlabel("SRNpb (dB)");
ylabel("TEB");
title('TEB en fonction du SNRpb - linear');
hold on
plot(datasNRZ(:,2:end),datasNRZ(:,1), 'g');
hold on
plot(datasNRZT(:,2:end),datasNRZT(:,1),'r');
hold on
% refx=datasRZ(:,2:end);
% refy = (1/2)*erfc(sqrt(refx));
% plot(refx,refy,'y');
% legend('RZ', ' NRZ', 'NRZT')



subplot(212);
semilogy(datasRZ(:,2),datasRZ(:,1),'b');
hold on
semilogy(datasNRZ(:,2),datasNRZ(:,1), 'g');
hold on
semilogy(datasNRZT(:,2),datasNRZT(:,1),'r');

legend('RZ', ' NRZ', 'NRZT')
xlabel("SRNpb (dB)");
ylabel("TEB (log)");
title('TEB en fonction du SNRpb - Log');
grid on;