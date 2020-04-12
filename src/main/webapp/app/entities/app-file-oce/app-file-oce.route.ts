import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, Routes, Router } from '@angular/router';
import { Observable, of, EMPTY } from 'rxjs';
import { flatMap } from 'rxjs/operators';

import { Authority } from 'app/shared/constants/authority.constants';
import { UserRouteAccessService } from 'app/core/auth/user-route-access-service';
import { IAppFileOce, AppFileOce } from 'app/shared/model/app-file-oce.model';
import { AppFileOceService } from './app-file-oce.service';
import { AppFileOceComponent } from './app-file-oce.component';
import { AppFileOceDetailComponent } from './app-file-oce-detail.component';
import { AppFileOceUpdateComponent } from './app-file-oce-update.component';

@Injectable({ providedIn: 'root' })
export class AppFileOceResolve implements Resolve<IAppFileOce> {
  constructor(private service: AppFileOceService, private router: Router) {}

  resolve(route: ActivatedRouteSnapshot): Observable<IAppFileOce> | Observable<never> {
    const id = route.params['id'];
    if (id) {
      return this.service.find(id).pipe(
        flatMap((appFile: HttpResponse<AppFileOce>) => {
          if (appFile.body) {
            return of(appFile.body);
          } else {
            this.router.navigate(['404']);
            return EMPTY;
          }
        })
      );
    }
    return of(new AppFileOce());
  }
}

export const appFileRoute: Routes = [
  {
    path: '',
    component: AppFileOceComponent,
    data: {
      authorities: [Authority.USER],
      pageTitle: 'jhipsterApp.appFile.home.title'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: ':id/view',
    component: AppFileOceDetailComponent,
    resolve: {
      appFile: AppFileOceResolve
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'jhipsterApp.appFile.home.title'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: 'new',
    component: AppFileOceUpdateComponent,
    resolve: {
      appFile: AppFileOceResolve
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'jhipsterApp.appFile.home.title'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: ':id/edit',
    component: AppFileOceUpdateComponent,
    resolve: {
      appFile: AppFileOceResolve
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'jhipsterApp.appFile.home.title'
    },
    canActivate: [UserRouteAccessService]
  }
];
