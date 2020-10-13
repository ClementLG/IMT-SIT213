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

snrpb_dB = -25:0.1:20;
E0= (10.^(snrpb_dB/10) .* 10.^(-110/10)) ./ 10.^(-40/10);

%Affichahe temporelle
subplot(211);
plot(datasRZ(:,2:end),datasRZ(:,1),'b');
hold on
plot(datasCRZ(:,2:end),datasCRZ(:,1),'c');
hold on
plot(datasNRZ(:,2:end),datasNRZ(:,1),'Color' ,[0 0.5 0.5]);
hold on
plot(datasCNRZ(:,2:end),datasCNRZ(:,1), 'g');
hold on
plot(datasNRZT(:,2:end),datasNRZT(:,1),'r');
hold on
plot(datasCNRZT(:,2:end),datasCNRZT(:,1), 'm');
grid on
title('TEB en fonction du SNRpb - linear');
hold on
yyaxis right
plot(snrpb_dB,E0, 'g--');
xlabel("SRNpb (dB)");
ylabel("TEB");
legend('RZ','CodRZ', ' NRZ','CodNRZ', 'NRZT','CodNRZT')

%Affichage log
figure(2);
semilogy(datasRZ(:,2),datasRZ(:,1),'b');
hold on
semilogy(datasCRZ(:,2),datasCRZ(:,1),'c');
hold on
semilogy(datasNRZ(:,2),datasNRZ(:,1),'Color' ,[[0, 0.5, 0]]);
hold on
semilogy(datasCNRZ(:,2),datasCNRZ(:,1),'g');
hold on
semilogy(datasRZ(:,2),datasNRZT(:,1),'r');
hold on
semilogy(datasCNRZT(:,2),datasCNRZT(:,1),'m');
hold on
axis([0 14 10^-5 1])
hold on
% %droite vertical à10^-3
horiz=ones(size(datasNRZT(:,2))).*10^(-3);
semilogy(datasNRZT(:,2),horiz,'k--');
hold on;
yyaxis right
semilogy(snrpb_dB,E0,'Color',[0.75, 0.75, 0]);
y = ylim; % current y-axis limits
plot([3.9 3.9],[y(1) y(2)], 'k--')
legend('RZ', 'CodRZ', 'NRZ', 'CodNRZ', 'NRZT', 'CodNRZT', 'Seuil Client', 'E0') %'CodRZ','CodNRZ', 'CodNRZT',
xlabel("SRNpb (dB)");
ylabel("Energie (J) ");
yyaxis left
ylabel("TEB (log)");
title('TEB en fonction du SNRpb - Log');
grid on;