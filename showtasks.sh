#!/usr/bin/env bash

fail() {
   echo "showtasks.sh: There were errors"
}

end() {
   echo "showtasks.sh: Work is finished"
}

if ./runcrud.sh; then
  open -a Safari http://localhost:8080/crud/v1/task/getTasks;
  end
else
  fail
fi