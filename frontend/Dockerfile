FROM node:18.17.1

WORKDIR /app

COPY package*.json .

RUN npm install

COPY . .

RUN sed -i 's/localhost/backend/g' package.json

EXPOSE 3000

ENTRYPOINT ["npm", "run", "dev", "--", "--host"]
