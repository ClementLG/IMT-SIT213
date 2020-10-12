clear all
clc
%Recuperation des DATA
RZ = importdata('C:\Users\clegruiec\OneDrive - RETIS\IMT\IMT-SIT213\extract\TEBenFctSNR\environnement2\RZ1Menv2Decal300.txt',';',2);
NRZ = importdata('C:\Users\clegruiec\OneDrive - RETIS\IMT\IMT-SIT213\extract\TEBenFctSNR\environnement2\NRZ1Menv2Decal300.txt',';',2);
NRZT = importdata('C:\Users\clegruiec\OneDrive - RETIS\IMT\IMT-SIT213\extract\TEBenFctSNR\environnement2\NRZT1Menv2Decal300.txt',';',2);
% CRZ = importdata('C:\Users\clegruiec\OneDrive - RETIS\IMT\IMT-SIT213\extract\TEBenFctSNR\CRZ.txt',';',2);
% CNRZ = importdata('C:\Users\clegruiec\OneDrive - RETIS\IMT\IMT-SIT213\extract\TEBenFctSNR\CNRZ.txt',';',2);
% CNRZT = importdata('C:\Users\clegruiec\OneDrive - RETIS\IMT\IMT-SIT213\extract\TEBenFctSNR\CNRZT.txt',';',2);
datasRZ = RZ.data;
datasNRZ = NRZ.data;
datasNRZT = NRZT.data;
% datasCRZ = CRZ.data;
% datasCNRZ = CNRZ.data;
% datasCNRZT = CNRZT.data;



%Affichage log
% figure(2);
semilogy(datasRZ(:,2),datasRZ(:,1),'b');
hold on
% semilogy(datasRZ(:,2),datasCRZ(:,1),'c');
% hold on
semilogy(datasNRZ(:,2),datasNRZ(:,1),'Color' ,[[0, 0.5, 0]]);
hold on
% semilogy(datasRZ(:,2),datasCNRZ(:,1),'g');
% hold on
semilogy(datasNRZT(:,2),datasNRZT(:,1),'r');
hold on
% semilogy(datasNRZT(:,2),datasCNRZT(:,1),'m');
% hold on
% axis([0 14 10^-5 1])
% hold on
legend('RZ', 'NRZ', 'NRZT')
% %droite horizontale à10^-3
 horiz=ones(size(datasNRZ(:,2))).*10^(-2);
 semilogy(datasNRZ(:,2),horiz,'k--');

%droite verticale
y = ylim; % current y-axis limits
plot([15 15],[y(1) y(2)], 'k--')

%legendes
%legend('RZ', 'CodRZ', 'NRZ', 'CodNRZ', 'NRZT', 'CodNRZT', 'Seuil Client', 'E0') %'CodRZ','CodNRZ', 'CodNRZT',

xlabel("SRNpb (dB)");
ylabel("TEB (log)");
title('TEB en fonction du SNRpb - Log');
grid on;