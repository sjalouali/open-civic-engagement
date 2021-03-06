import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, Routes, Router } from '@angular/router';
import { Observable, of, EMPTY } from 'rxjs';
import { flatMap } from 'rxjs/operators';

import { Authority } from 'app/shared/constants/authority.constants';
import { UserRouteAccessService } from 'app/core/auth/user-route-access-service';
import { IOrganisationOce, OrganisationOce } from 'app/shared/model/organisation-oce.model';
import { OrganisationOceService } from './organisation-oce.service';
import { OrganisationOceComponent } from './organisation-oce.component';
import { OrganisationOceDetailComponent } from './organisation-oce-detail.component';
import { OrganisationOceUpdateComponent } from './organisation-oce-update.component';

@Injectable({ providedIn: 'root' })
export class OrganisationOceResolve implements Resolve<IOrganisationOce> {
  constructor(private service: OrganisationOceService, private router: Router) {}

  resolve(route: ActivatedRouteSnapshot): Observable<IOrganisationOce> | Observable<never> {
    const id = route.params['id'];
    if (id) {
      return this.service.find(id).pipe(
        flatMap((organisation: HttpResponse<OrganisationOce>) => {
          if (organisation.body) {
            return of(organisation.body);
          } else {
            this.router.navigate(['404']);
            return EMPTY;
          }
        })
      );
    }
    return of(new OrganisationOce());
  }
}

export const organisationRoute: Routes = [
  {
    path: '',
    component: OrganisationOceComponent,
    data: {
      authorities: [Authority.ADMIN],
      pageTitle: 'openCivicEngagementApp.organisation.home.title'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: ':id/view',
    component: OrganisationOceDetailComponent,
    resolve: {
      organisation: OrganisationOceResolve
    },
    data: {
      authorities: [Authority.ADMIN],
      pageTitle: 'openCivicEngagementApp.organisation.home.title'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: 'new',
    component: OrganisationOceUpdateComponent,
    resolve: {
      organisation: OrganisationOceResolve
    },
    data: {
      authorities: [Authority.ADMIN],
      pageTitle: 'openCivicEngagementApp.organisation.home.title'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: ':id/edit',
    component: OrganisationOceUpdateComponent,
    resolve: {
      organisation: OrganisationOceResolve
    },
    data: {
      authorities: [Authority.ADMIN],
      pageTitle: 'openCivicEngagementApp.organisation.home.title'
    },
    canActivate: [UserRouteAccessService]
  }
];
