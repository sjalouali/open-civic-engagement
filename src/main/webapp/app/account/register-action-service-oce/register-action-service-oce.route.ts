import { Route } from '@angular/router';

import { ActionServiceOceUpdateComponent } from './register-action-service-oce-update.component';

export const actionServiceRoute: Route = {
  path: 'register-demande',
  component: ActionServiceOceUpdateComponent,
  data: {
    authorities: [],
    pageTitle: 'register.title'
  }
};
