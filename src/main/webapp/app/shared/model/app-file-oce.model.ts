export interface IAppFileOce {
  id?: number;
  name?: string;
  path?: string;
  fileSize?: string;
  extention?: string;
  commentOceId?: number;
  actionServiceId?: number;
}

export class AppFileOce implements IAppFileOce {
  constructor(
    public id?: number,
    public name?: string,
    public path?: string,
    public fileSize?: string,
    public extention?: string,
    public commentOceId?: number,
    public actionServiceId?: number
  ) {}
}
