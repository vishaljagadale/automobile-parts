# AutopartsMarketplaceFe

This project was generated with [Angular CLI](https://github.com/angular/angular-cli) version 18.2.10.

## Development server

Run `ng serve` for a dev server. Navigate to `http://localhost:4200/`. The application will automatically reload if you change any of the source files.

## Code scaffolding

Run `ng generate component component-name` to generate a new component. You can also use `ng generate directive|pipe|service|class|guard|interface|enum|module`.

## Build

Run `ng build` to build the project. The build artifacts will be stored in the `dist/` directory.

## Running unit tests

Run `ng test` to execute the unit tests via [Karma](https://karma-runner.github.io).

## Running end-to-end tests

Run `ng e2e` to execute the end-to-end tests via a platform of your choice. To use this command, you need to first add a package that implements end-to-end testing capabilities.

## Further help

To get more help on the Angular CLI use `ng help` or go check out the [Angular CLI Overview and Command Reference](https://angular.dev/tools/cli) page.

## Deploying to Netlify

1. Build the project for production:
   ```
   ng build --configuration production
   ```
   The build output will be in `dist/autoparts-marketplace-fe`.

2. Go to [Netlify](https://app.netlify.com/) and sign up or log in.

3. Create a new site and choose the `dist/autoparts-marketplace-fe` folder as the publish directory.

4. Ensure the `_redirects` file is present in the build output (it will be copied from `public/`). This enables Angular routing on Netlify.

5. Deploy the site. Netlify will provide a public URL for your app.

6. For custom domains, follow Netlify’s instructions in the dashboard.