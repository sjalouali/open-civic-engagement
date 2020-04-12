import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';

@NgModule({
  imports: [
    RouterModule.forChild([
      {
        path: 'organisation-oce',
        loadChildren: () => import('./organisation-oce/organisation-oce.module').then(m => m.OpenCivicEngagementOrganisationOceModule)
      },
      {
        path: 'action-service-oce',
        loadChildren: () => import('./action-service-oce/action-service-oce.module').then(m => m.OpenCivicEngagementActionServiceOceModule)
      },
      {
        path: 'type-service-oce',
        loadChildren: () => import('./type-service-oce/type-service-oce.module').then(m => m.OpenCivicEngagementTypeServiceOceModule)
      },
      {
        path: 'app-file-oce',
        loadChildren: () => import('./app-file-oce/app-file-oce.module').then(m => m.OpenCivicEngagementAppFileOceModule)
      },
      {
        path: 'comment-oce',
        loadChildren: () => import('./comment-oce/comment-oce.module').then(m => m.OpenCivicEngagementCommentOceModule)
      }
      /* jhipster-needle-add-entity-route - JHipster will add entity modules routes here */
    ])
  ]
})
export class OpenCivicEngagementEntityModule {}
