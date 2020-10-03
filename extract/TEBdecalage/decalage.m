%1 trajet parasite
clear all
clc
RZ1T1 = importdata('C:\Users\clegruiec\OneDrive - RETIS\IMT\IMT-SIT213\extract\TEBdecalage\NRZT-m100-1traj-coeff1.txt',';',2);
RZ1T075 = importdata('C:\Users\clegruiec\OneDrive - RETIS\IMT\IMT-SIT213\extract\TEBdecalage\NRZT-m100-1traj-coeff075.txt',';',2);
RZ1T05 = importdata('C:\Users\clegruiec\OneDrive - RETIS\IMT\IMT-SIT213\extract\TEBdecalage\NRZT-m100-1traj-coeff05.txt',';',2);
CRZ1T1 = importdata('C:\Users\clegruiec\OneDrive - RETIS\IMT\IMT-SIT213\extract\TEBdecalage\NRZTcodeur-m100-1traj-coeff1.txt',';',2);
CRZ1T075 = importdata('C:\Users\clegruiec\OneDrive - RETIS\IMT\IMT-SIT213\extract\TEBdecalage\NRZTcodeur-m100-1traj-coeff075.txt',';',2);
CRZ1T05 = importdata('C:\Users\clegruiec\OneDrive - RETIS\IMT\IMT-SIT213\extract\TEBdecalage\NRZTcodeur-m100-1traj-coeff05.txt',';',2);
datasRZ1T1 = RZ1T1.data;
datasRZ1T075 = RZ1T075.data;
datasRZ1T05 = RZ1T05.data;
datasCRZ1T1 = CRZ1T1.data;
datasCRZ1T075 = CRZ1T075.data;
datasCRZ1T05 = CRZ1T05.data;

figure(1);
subplot(211);
plot(datasRZ1T1(:,2:end),datasRZ1T1(:,1),'b');
hold on
plot(datasCRZ1T1(:,2:end),datasCRZ1T1(:,1),'k');
hold on
% plot(datasRZ1T075(:,2:end),datasRZ1T075(:,1), 'm');
% hold on
% plot(datasCRZ1T075(:,2:end),datasCRZ1T075(:,1), 'g');
% hold on
% plot(datasRZ1T05(:,2:end),datasRZ1T05(:,1),'r');
% hold on
% plot(datasCRZ1T05(:,2:end),datasCRZ1T05(:,1),'r');
% grid on
xlabel("decalage en nombre d'echantillon");
ylabel("TEB");
title('TEB en fonction du decalage (1 trajet indirect) - linear');
legend('coeff 1', ' coeff 0.75', 'coeff 0.5')
hold on


subplot(212);
semilogy(datasRZ1T1(:,2),datasRZ1T1(:,1),'b');
hold on
semilogy(datasCRZ1T1(:,2),datasCRZ1T1(:,1),'k');
hold on
% semilogy(datasRZ1T075(:,2),datasRZ1T075(:,1), 'm');
% hold on
% semilogy(datasCRZ1T075(:,2),datasCRZ1T075(:,1),'g');
% hold on
% semilogy(datasRZ1T05(:,2),datasRZ1T05(:,1),'r');
% hold on
% semilogy(datasCRZ1T05(:,2),datasCRZ1T05(:,1),'r');
legend('coeff 1', ' coeff 0.75', 'coeff 0.5')
xlabel("decalage en nombre d'echantillon");
ylabel("TEB (log)");
title('TEB en fonction du decalage (1 trajet indirect)- Log');
grid on;

%2 trajets parasites

RZ2T1 = importdata('C:\Users\clegruiec\OneDrive - RETIS\IMT\IMT-SIT213\extract\TEBdecalage\NRZT-m100-2traj-coeff1.txt',';',2);
RZ2T075 = importdata('C:\Users\clegruiec\OneDrive - RETIS\IMT\IMT-SIT213\extract\TEBdecalage\NRZT-m100-2traj-coeff075.txt',';',2);
RZ2T05 = importdata('C:\Users\clegruiec\OneDrive - RETIS\IMT\IMT-SIT213\extract\TEBdecalage\NRZT-m100-2traj-coeff05.txt',';',2);

datasRZ2T1 = RZ2T1.data;
datasRZ2T075 = RZ2T075.data;
datasRZ2T05 = RZ2T05.data;

figure(2);
subplot(211);
plot(datasRZ2T1(:,2:end),datasRZ2T1(:,1),'b');
hold on
plot(datasRZ2T075(:,2:end),datasRZ2T075(:,1), 'm');
hold on
plot(datasRZ2T05(:,2:end),datasRZ2T05(:,1),'r');
grid on
xlabel("decalage en nombre d'echantillon");
ylabel("TEB");
title('TEB en fonction du decalage (2 trajet de direction opposées) - linear');
legend('coeff 1', ' coeff 0.75', 'coeff 0.5')
hold on


subplot(212);
semilogy(datasRZ2T1(:,2),datasRZ2T1(:,1),'b');
hold on
semilogy(datasRZ2T075(:,2),datasRZ2T075(:,1), 'g');
hold on
semilogy(datasRZ2T05(:,2),datasRZ2T05(:,1),'r');
legend('coeff 1', ' coeff 0.75', 'coeff 0.5')
xlabel("decalage en nombre d'echantillon");
ylabel("TEB (log)");
title('TEB en fonction du decalage (2 trajet de direction opposées) - Log');
grid on;