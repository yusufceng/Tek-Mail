#!/bin/bash
array=$(ls)
for value in "${array[@]}"
do
     echo $value
done
echo "done"
echo ${array[1]}
