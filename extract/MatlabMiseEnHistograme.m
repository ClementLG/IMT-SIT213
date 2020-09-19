%java  Simulateur  -seed  40  -form  NRZ  -ampl  -5  5  -snrpb  -20
%java  Simulateur  -seed  40  -form  RZ  -ampl  -5  5  -snrpb  -20
%java  Simulateur  -seed  40  -form  NRZT  -ampl  -5  5  -snrpb  -20
%100K essaies

%mettre la source des données
fid = fopen('C:\Users\clegruiec\OneDrive - RETIS\IMT\IMT-SIT213\extract\NRZT_-5_5_seed40_SNRPB-20.txt', 'r');
T = fscanf(fid, '%f %f\n', [2 Inf]);
histogram(T)
fclose(fid);