import { Route } from '@angular/router';

import { PropositionServiceOceUpdateComponent } from './register-proposition-service-oce-update.component';

export const propositionServiceRoute: Route = {
  path: 'register-proposition',
  component: PropositionServiceOceUpdateComponent,
  data: {
    authorities: [],
    pageTitle: 'register.title'
  }
};
