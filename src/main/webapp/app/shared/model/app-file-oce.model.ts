export interface IAppFileOce {
  id?: number;
  name?: string;
  path?: string;
  fileSize?: string;
  extention?: string;
  commentId?: number;
  actionServiceId?: number;
}

export class AppFileOce implements IAppFileOce {
  constructor(
    public id?: number,
    public name?: string,
    public path?: string,
    public fileSize?: string,
    public extention?: string,
    public commentId?: number,
    public actionServiceId?: number
  ) {}
}
