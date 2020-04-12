import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, Routes, Router } from '@angular/router';
import { Observable, of, EMPTY } from 'rxjs';
import { flatMap } from 'rxjs/operators';

import { Authority } from 'app/shared/constants/authority.constants';
import { UserRouteAccessService } from 'app/core/auth/user-route-access-service';
import { ITypeServiceOce, TypeServiceOce } from 'app/shared/model/type-service-oce.model';
import { TypeServiceOceService } from './type-service-oce.service';
import { TypeServiceOceComponent } from './type-service-oce.component';
import { TypeServiceOceDetailComponent } from './type-service-oce-detail.component';
import { TypeServiceOceUpdateComponent } from './type-service-oce-update.component';

@Injectable({ providedIn: 'root' })
export class TypeServiceOceResolve implements Resolve<ITypeServiceOce> {
  constructor(private service: TypeServiceOceService, private router: Router) {}

  resolve(route: ActivatedRouteSnapshot): Observable<ITypeServiceOce> | Observable<never> {
    const id = route.params['id'];
    if (id) {
      return this.service.find(id).pipe(
        flatMap((typeService: HttpResponse<TypeServiceOce>) => {
          if (typeService.body) {
            return of(typeService.body);
          } else {
            this.router.navigate(['404']);
            return EMPTY;
          }
        })
      );
    }
    return of(new TypeServiceOce());
  }
}

export const typeServiceRoute: Routes = [
  {
    path: '',
    component: TypeServiceOceComponent,
    data: {
      authorities: [Authority.USER],
      pageTitle: 'jhipsterApp.typeService.home.title'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: ':id/view',
    component: TypeServiceOceDetailComponent,
    resolve: {
      typeService: TypeServiceOceResolve
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'jhipsterApp.typeService.home.title'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: 'new',
    component: TypeServiceOceUpdateComponent,
    resolve: {
      typeService: TypeServiceOceResolve
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'jhipsterApp.typeService.home.title'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: ':id/edit',
    component: TypeServiceOceUpdateComponent,
    resolve: {
      typeService: TypeServiceOceResolve
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'jhipsterApp.typeService.home.title'
    },
    canActivate: [UserRouteAccessService]
  }
];
