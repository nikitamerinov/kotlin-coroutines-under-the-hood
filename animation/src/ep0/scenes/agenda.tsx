import {Layout, makeScene2D, Node, Txt} from '@motion-canvas/2d';
import {createRef, createRefArray, Direction, slideTransition, waitFor, waitUntil} from '@motion-canvas/core';
import {Caption, Text} from '../../common';

export default makeScene2D(function* (view) {
  const title = createRef<Txt>();
  const titleWrapper = createRef<Node>();
  const textLines = createRefArray<Txt>()

  view.add(
    <>
      <Caption ref={title} text={'Introduction to the series'} />
      {/*<Layout layout size={'100%'} padding={150} direction={'column'} alignItems={'center'} justifyContent={'center'}>*/}
        <Layout layout direction={'column'} gap={150}>
          <Text ref={textLines} text={'What topics will be covered'} />
          <Text ref={textLines} text={'What will not be covered'} />
          <Text ref={textLines} text={'Additional notes'} />
        </Layout>
      {/*</Layout>*/}
    </>
  );

  prepare(textLines);

  yield* slideTransition(Direction.Right);

  yield* waitUntil('start');

  title().size(title().size());
  yield* title().text('', 1);

  for (const textLine of textLines) {
    const text = textLine.text();
    textLine.size(textLine.size());
    textLine.text('');
    textLine.opacity(1);
    yield textLine.text(text, 1);
  }


  yield* waitUntil('end');
});

function prepare(textLines: Txt[]) {
  for (const textLine of textLines) {
    textLine.opacity(0);
  }
}
