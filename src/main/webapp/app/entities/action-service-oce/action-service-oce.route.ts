import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, Routes, Router } from '@angular/router';
import { Observable, of, EMPTY } from 'rxjs';
import { flatMap } from 'rxjs/operators';

import { Authority } from 'app/shared/constants/authority.constants';
import { UserRouteAccessService } from 'app/core/auth/user-route-access-service';
import { IActionServiceOce, ActionServiceOce } from 'app/shared/model/action-service-oce.model';
import { ActionServiceOceService } from './action-service-oce.service';
import { ActionServiceOceComponent } from './action-service-oce.component';
import { ActionServiceOceDetailComponent } from './action-service-oce-detail.component';
import { ActionServiceOceUpdateComponent } from './action-service-oce-update.component';

@Injectable({ providedIn: 'root' })
export class ActionServiceOceResolve implements Resolve<IActionServiceOce> {
  constructor(private service: ActionServiceOceService, private router: Router) {}

  resolve(route: ActivatedRouteSnapshot): Observable<IActionServiceOce> | Observable<never> {
    const id = route.params['id'];
    if (id) {
      return this.service.find(id).pipe(
        flatMap((actionService: HttpResponse<ActionServiceOce>) => {
          if (actionService.body) {
            return of(actionService.body);
          } else {
            this.router.navigate(['404']);
            return EMPTY;
          }
        })
      );
    }
    return of(new ActionServiceOce());
  }
}

export const actionServiceRoute: Routes = [
  {
    path: '',
    component: ActionServiceOceComponent,
    data: {
      authorities: [Authority.USER],
      pageTitle: 'jhipsterApp.actionService.home.title'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: ':id/view',
    component: ActionServiceOceDetailComponent,
    resolve: {
      actionService: ActionServiceOceResolve
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'jhipsterApp.actionService.home.title'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: 'new',
    component: ActionServiceOceUpdateComponent,
    resolve: {
      actionService: ActionServiceOceResolve
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'jhipsterApp.actionService.home.title'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: ':id/edit',
    component: ActionServiceOceUpdateComponent,
    resolve: {
      actionService: ActionServiceOceResolve
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'jhipsterApp.actionService.home.title'
    },
    canActivate: [UserRouteAccessService]
  }
];
