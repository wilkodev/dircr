dircr
=====

###Java command line program to create dir structure from a file.

####Usage: dircr filename.txt

####if filename.txt looks like this:


root  
 subdir  
 subdir2  
  sub-subdir  
  sub-subdir2  
 subdir3  
  
dircr will create directories like this:  
./root/  
./root/subdir/  
./root/subdir2/  
./root/subdir2/sub-subdir/  
./root/subdir2/sub-subdir2/  
./root/subdir3/  