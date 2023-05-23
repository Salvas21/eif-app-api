#!/bin/bash
git pull
rm -rf /root/eif-app/dist
ng build --configuration production
rm -rf /var/www/eif-app.salvas.tech
cp -r /root/eif-app/dist/eif-app /var/www/eif-app.salvas.tech
sudo chown -R "$USER":www-data /var/www/eif-app.salvas.tech
sudo chmod -R 0755 /var/www/eif-app.salvas.tech
systemctl restart nginx