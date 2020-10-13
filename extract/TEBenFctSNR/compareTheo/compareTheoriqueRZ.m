clear
%courbe de reference pour TEB de NRZ
EbN0_dB = 0:0.1:10.5;
EbN0 = 10.^(EbN0_dB/10);
BER = 1/2.*erfc(sqrt(EbN0));

%courbe du simulateur
% NRZ_10e = importdata('C:\Users\clegruiec\OneDrive - RETIS\IMT\IMT-SIT213\extract\TEBenFctSNR\compareTheo\NRZ-1M-10ech.txt',';',2);
% datasNRZ_10e = NRZ_10e.data;
% NRZ_20e = importdata('C:\Users\clegruiec\OneDrive - RETIS\IMT\IMT-SIT213\extract\TEBenFctSNR\compareTheo\NRZ-1M-20ech.txt',';',2);
% datasNRZ_20e = NRZ_20e.data;
%NRZ_30e = importdata('C:\Users\clegruiec\OneDrive - RETIS\IMT\IMT-SIT213\extract\TEBenFctSNR\compareTheo\NRZ-500k-30ech.txt',';',2);
%datasNRZ_30e = NRZ_30e.data;
NRZ_40e = importdata('C:\Users\clegruiec\OneDrive - RETIS\IMT\IMT-SIT213\extract\TEBenFctSNR\compareTheo\new.txt',';',2);
datasNRZ_40e = NRZ_40e.data;
% NRZ_50e = importdata('C:\Users\clegruiec\OneDrive - RETIS\IMT\IMT-SIT213\extract\TEBenFctSNR\compareTheo\NRZ-1M-50ech.txt',';',2);
% datasNRZ_50e = NRZ_50e.data;
% NRZ_60e = importdata('C:\Users\clegruiec\OneDrive - RETIS\IMT\IMT-SIT213\extract\TEBenFctSNR\compareTheo\NRZ-100k-60ech.txt',';',2);
% datasNRZ_60e = NRZ_60e.data;

% RZ2 = importdata('C:\Users\clegruiec\OneDrive - RETIS\IMT\IMT-SIT213\extract\TEBenFctSNR\export.txt',';',2);
% datasRZ2 = RZ2.data;

semilogy(EbN0_dB,BER, 'r')
% hold on
% semilogy(datasNRZ_10e(:,2),datasNRZ_10e(:,1),'-.');
% hold on
% semilogy(datasNRZ_20e(:,2),datasNRZ_20e(:,1),'-.');
% hold on
% semilogy(datasNRZ_30e(:,2),datasNRZ_30e(:,1),'-.');
hold on
semilogy(datasNRZ_40e(:,2),datasNRZ_40e(:,1),'-.');
% hold on
% semilogy(datasNRZ_50e(:,2),datasNRZ_50e(:,1),'-.');
% hold on
% semilogy(datasNRZ_60e(:,2),datasNRZ_60e(:,1),'-.');

% hold on
% semilogy(datasRZ2(:,2),datasRZ2(:,1),'b');
axis([0 10 10^-5 0.15])
xlabel("SNRpb (dB)");
ylabel("TEB");
title('NRZ - Comparatif entre la courbe théroque et notre simulation');
%legend('Theorique', ' Simulée 10ech', ' Simulée 20ech',  ' Simulée 30ech',  ' Simulée 40ech',  ' Simulée 50ech',  ' Simulée 60ech')
legend('Theorique', ' Simulée');
grid on
