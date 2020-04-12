import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';

@NgModule({
  imports: [
    RouterModule.forChild([
      {
        path: 'organisation-oce',
        loadChildren: () => import('./organisation-oce/organisation-oce.module').then(m => m.JhipsterOrganisationOceModule)
      },
      {
        path: 'action-service-oce',
        loadChildren: () => import('./action-service-oce/action-service-oce.module').then(m => m.JhipsterActionServiceOceModule)
      },
      {
        path: 'type-service-oce',
        loadChildren: () => import('./type-service-oce/type-service-oce.module').then(m => m.JhipsterTypeServiceOceModule)
      },
      {
        path: 'app-file-oce',
        loadChildren: () => import('./app-file-oce/app-file-oce.module').then(m => m.JhipsterAppFileOceModule)
      },
      {
        path: 'comment-oce',
        loadChildren: () => import('./comment-oce/comment-oce.module').then(m => m.JhipsterCommentOceModule)
      }
      /* jhipster-needle-add-entity-route - JHipster will add entity modules routes here */
    ])
  ]
})
export class JhipsterEntityModule {}
