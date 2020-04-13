import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, Routes, Router } from '@angular/router';
import { Observable, of, EMPTY } from 'rxjs';
import { flatMap } from 'rxjs/operators';

import { Authority } from 'app/shared/constants/authority.constants';
import { UserRouteAccessService } from 'app/core/auth/user-route-access-service';
import { ICommentOceOce, CommentOceOce } from 'app/shared/model/comment-oce-oce.model';
import { CommentOceOceService } from './comment-oce-oce.service';
import { CommentOceOceComponent } from './comment-oce-oce.component';
import { CommentOceOceDetailComponent } from './comment-oce-oce-detail.component';
import { CommentOceOceUpdateComponent } from './comment-oce-oce-update.component';

@Injectable({ providedIn: 'root' })
export class CommentOceOceResolve implements Resolve<ICommentOceOce> {
  constructor(private service: CommentOceOceService, private router: Router) {}

  resolve(route: ActivatedRouteSnapshot): Observable<ICommentOceOce> | Observable<never> {
    const id = route.params['id'];
    if (id) {
      return this.service.find(id).pipe(
        flatMap((commentOce: HttpResponse<CommentOceOce>) => {
          if (commentOce.body) {
            return of(commentOce.body);
          } else {
            this.router.navigate(['404']);
            return EMPTY;
          }
        })
      );
    }
    return of(new CommentOceOce());
  }
}

export const commentOceRoute: Routes = [
  {
    path: '',
    component: CommentOceOceComponent,
    data: {
      authorities: [Authority.USER],
      pageTitle: 'openCivicEngagementApp.commentOce.home.title'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: ':id/view',
    component: CommentOceOceDetailComponent,
    resolve: {
      commentOce: CommentOceOceResolve
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'openCivicEngagementApp.commentOce.home.title'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: 'new',
    component: CommentOceOceUpdateComponent,
    resolve: {
      commentOce: CommentOceOceResolve
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'openCivicEngagementApp.commentOce.home.title'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: ':id/edit',
    component: CommentOceOceUpdateComponent,
    resolve: {
      commentOce: CommentOceOceResolve
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'openCivicEngagementApp.commentOce.home.title'
    },
    canActivate: [UserRouteAccessService]
  }
];
