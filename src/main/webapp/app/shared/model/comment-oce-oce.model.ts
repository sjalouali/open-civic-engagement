import { Moment } from 'moment';

export interface ICommentOceOce {
  id?: number;
  content?: any;
  commentDate?: Moment;
  userId?: number;
}

export class CommentOceOce implements ICommentOceOce {
  constructor(public id?: number, public content?: any, public commentDate?: Moment, public userId?: number) {}
}
