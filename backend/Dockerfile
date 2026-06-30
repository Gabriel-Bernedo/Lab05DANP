# Multi-stage Dockerfile
FROM node:22-alpine AS builder

WORKDIR /app

# Install dependencies
COPY package*.json ./
RUN npm install

# Copy source and Prisma config
COPY . .

# Generate Prisma client
RUN npx prisma generate

# Build the application
RUN npm run build

# --- Production Image ---
FROM node:22-alpine

WORKDIR /app

# Copy production dependencies
COPY package*.json ./
RUN npm install --omit=dev

# Copy built app and prisma schema/config
COPY --from=builder /app/dist ./dist
COPY --from=builder /app/prisma ./prisma
COPY --from=builder /app/prisma.config.ts ./prisma.config.ts

# Generate Prisma client for production env
RUN npx prisma generate

EXPOSE 3000

# The command to start the app in Dokploy will be overridden in docker-compose.yml
# but we provide a default here
CMD ["npm", "run", "start:prod"]
