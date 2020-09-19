%java  Simulateur  -seed  40  -form  NRZ  -ampl  -5  5  -snrpb  -20
%100K essaies

fid = fopen('C:\Users\clegruiec\OneDrive - RETIS\IMT\IMT-SIT213\extract\test.txt', 'r');
T = fscanf(fid, '%f %f\n', [2 Inf]);
histogram(T)
fclose(fid);