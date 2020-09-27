
%courbe de reference pour TEB de 
EbN0_dB = 0:0.1:10;
EbN0 = 10.^(EbN0_dB/10);
BER = 1/2.*erfc(sqrt(EbN0));

%courbe du simulateur
RZ = importdata('C:\Users\clegruiec\OneDrive - RETIS\IMT\IMT-SIT213\extract\TEBenFctSNR\RZ-1M-compareTheorique.txt',';',2);
datasRZ = RZ.data;

semilogy(EbN0_dB,BER, 'r')
hold on
semilogy(datasRZ(:,2),datasRZ(:,1),'b');

xlabel("SRNpb (dB)");
ylabel("TEB");
title('RZ - Comparatif entre la courbe th�roque et notre simulation');
legend('Theorique', ' Simul�e')
grid on
