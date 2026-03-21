# Flujo de Trabajo con Git por Ambientes  
(Develop - QA - Main)

---

## Contexto

Se implementó una estrategia de control de versiones basada en ambientes separados (develop, qa, main), utilizando ramas por Historia de Usuario (HU) y Pull Request controladas en GitHub.

---

## 1. Creación de ramas principales

Primero me ubiqué en la rama principal del proyecto:

```bash
git switch main
Creación de la rama develop
git switch -c develop
git push -u origin develop
Creación de la rama qa
git switch main
git switch -c qa
git push -u origin qa
Resultado

Se establecieron correctamente las ramas principales del repositorio:

main
develop
qa
2. Ejecución del flujo por Historia de Usuario (HU)

Se aplicó el flujo completo para una HU (ejemplo: HU-01) en los tres ambientes.

A. Desarrollo (Develop)
Creación de rama desde develop
git switch develop
git switch -c HU-01-develop
Desarrollo de la funcionalidad
git add .
git commit -m "HU-01 desarrollo"
Publicación de la rama
git push -u origin HU-01-develop
Pull Request en GitHub

Configuración realizada:

base: develop
compare: HU-01-develop
Merge realizado desde GitHub
No se utilizó git merge local
B. QA (Pruebas)
Creación de rama desde qa
git switch qa
git switch -c HU-01-qa
Reaplicación del cambio

Se siguieron las reglas del flujo:

No copiar archivos entre ramas
No realizar merge entre ambientes
Reaplicar manualmente los cambios realizados en develop
Commit en QA
git add .
git commit -m "HU-01 pruebas QA"
Publicación de la rama
git push -u origin HU-01-qa
Pull Request en GitHub
base: qa
compare: HU-01-qa
Merge realizado desde GitHub
No se utilizó git merge
C. Producción (Main)
Creación de rama desde main
git switch main
git switch -c HU-01-main
Reaplicación del cambio validado

Se implementó el cambio validado en QA:

Se crearon manualmente los archivos
Se respetó la independencia de ambientes
Commit en producción
git add .
git commit -m "HU-01 producción"
Publicación de la rama
git push -u origin HU-01-main
Pull Request en GitHub
base: main
compare: HU-01-main
Merge realizado desde GitHub
No se utilizó git merge
Reglas aplicadas durante la implementación
Acciones prohibidas
git merge qa
git merge develop
git merge main

También se evitó:

Cruces entre ambientes
develop → qa
qa → main
Mezcla de ramas HU entre ambientes
Buenas prácticas aplicadas

Se respetó la promoción correcta de cambios:

HU-01-develop → develop  
HU-01-qa      → qa  
HU-01-main    → main  
Modelo mental aplicado

Cada ambiente se manejó de forma independiente:

develop ← HU-01-develop  
qa      ← HU-01-qa  
main    ← HU-01-main  
Sin cruces
Solo Pull Request dentro del mismo ambiente
Sincronización de ramas

Después de cada merge en GitHub, se actualizaron las ramas locales:

git switch develop
git pull origin develop

git switch qa
git pull origin qa

git switch main
git pull origin main