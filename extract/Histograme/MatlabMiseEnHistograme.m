%mettre la source des donn�es
fid = fopen('D:\Guillaume\IdeaProjects\IMT-SIT213\extract\Histograme\Bruit.txt', 'r');
T = fscanf(fid, '%f;\n');
histogram(T)
xlabel("Value");
ylabel("Number of points");
fclose(fid);
