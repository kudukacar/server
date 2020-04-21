#!/bin/bash

sudo apt update
sudo apt install autoconf bison build-essential libssl-dev libyaml-dev libreadline6-dev zlib1g-dev libncurses5-dev libffi-dev libgdbm6 libgdbm-dev
sudo apt-get install rubygems ruby-dev
sudo gem install bundler:1.17.2
git clone https://github.com/rbenv/rbenv.git ~/.rbenv
export PATH="$HOME/.rbenv/bin:$PATH"
eval "$(rbenv init -)"
git clone https://github.com/rbenv/ruby-build.git ~/.rbenv/plugins/ruby-build
PATH="$HOME/.rbenv/bin:$PATH"
rbenv install 2.5.0
rbenv global 2.5.0
bundle install